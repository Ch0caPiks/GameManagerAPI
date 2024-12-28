package fr.chocapiks.gamemanagerapi.utils;

import fr.chocapiks.gamemanagerapi.core.EventManager;
import fr.chocapiks.gamemanagerapi.core.events.*;
import fr.chocapiks.gamemanagerapi.types.Trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public class AsyncTimer {
    private final String name;
    private final int duration; // Duration in seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> futureTask;
    private long startTime;
    private long remainingTime;
    private final List<Trigger> triggers = new ArrayList<>();
    private static final EventManager eventManager = EventManager.getInstance();

    public AsyncTimer(String name, int duration) {
        this.name = name;
        this.duration = duration;
        logCreation();
    }

    public void start(Runnable task) {
        if (futureTask != null && !futureTask.isDone()) {
            throw new IllegalStateException("Timer is already running");
        }
        startTime = System.currentTimeMillis();
        remainingTime = duration;
        futureTask = scheduler.schedule(() -> {
            task.run();
            shutdown();
        }, remainingTime, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::checkTriggers, 0, 1, TimeUnit.SECONDS);
        TimerStarted event = new TimerStarted(this);
        eventManager.callEvent(event);
    }

    public void pause() {
        if (futureTask != null && !futureTask.isDone()) {
            futureTask.cancel(true);
            remainingTime -= (System.currentTimeMillis() - startTime) / 1000;
            TimerPaused event = new TimerPaused(this);
            eventManager.callEvent(event);
        }
    }

    public void resume(Runnable task) {
        if (futureTask != null && !futureTask.isDone()) {
            throw new IllegalStateException("Timer is already running");
        }
        startTime = System.currentTimeMillis();
        futureTask = scheduler.schedule(() -> {
            task.run();
            shutdown();
        }, remainingTime, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::checkTriggers, 0, 1, TimeUnit.SECONDS);
        TimerResumed event = new TimerResumed(this);
        eventManager.callEvent(event);
    }

    public void cancel() {
        if (futureTask != null && !futureTask.isDone()) {
            futureTask.cancel(true);
            TimerCanceled event = new TimerCanceled(this);
            eventManager.callEvent(event);
            shutdown(); // Shutdown the scheduler
        }
    }

    public boolean isRunning() {
        return futureTask != null && !futureTask.isDone();
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getRemainingTime() {
        if (futureTask == null || futureTask.isDone()) {
            return 0;
        }
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        return remainingTime - elapsedTime;
    }

    public void addTrigger(int second, int triggerId, String triggerName) {
        triggers.add(new Trigger(this, second, triggerId, triggerName));
    }

    public List<Trigger> getTriggers() {
        return new ArrayList<>(triggers);
    }

    public Trigger getTriggerById(int triggerId) {
        return triggers.stream()
                .filter(trigger -> trigger.getId() == triggerId)
                .findFirst()
                .orElse(null);
    }

    public Trigger getTriggerByName(String triggerName) {
        return triggers.stream()
                .filter(trigger -> trigger.getName().equals(triggerName))
                .findFirst()
                .orElse(null);
    }

    private void checkTriggers() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        for (Trigger trigger : triggers) {
            if (trigger.getSecond() == elapsedTime) {
                TriggerRelease event = new TriggerRelease(trigger);
                eventManager.callEvent(event);
            }
        }
    }

    private void logCreation() {
        TimerCreated event = new TimerCreated(this);
        eventManager.callEvent(event);
    }
}