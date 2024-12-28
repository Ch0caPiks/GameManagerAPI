package fr.chocapiks.gamemanagerapi.core.listeners;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.GEventHandler;
import fr.chocapiks.gamemanagerapi.core.GEventListener;
import fr.chocapiks.gamemanagerapi.core.events.TimerCreated;

import java.util.logging.Logger;

public class LTimer implements GEventListener {

    private final static Logger logger = GameManagerApi.getInstance().logger;

    @GEventHandler
    public void onTimerCreated(TimerCreated e) {
        logger.info(String.format("[+] (TimerCreated) -> Timer '%s' created, Duration: %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

    @GEventHandler
    public void onTimerCancelled(TimerCreated e) {
        logger.info(String.format("[+] (TimerCancelled) -> Timer '%s' cancelled, which lasted %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

    @GEventHandler
    public void onTimerStarted(TimerCreated e) {
        logger.info(String.format("[+] (TimerStarted) -> Timer '%s' started, Duration: %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

    @GEventHandler
    public void onTimerStopped(TimerCreated e) {
        logger.info(String.format("[+] (TimerStopped) -> Timer '%s' stopped, which lasted %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

    @GEventHandler
    public void onTimerPaused(TimerCreated e) {
        logger.info(String.format("[+] (TimerPaused) -> Timer '%s' paused, which lasted %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

    @GEventHandler
    public void onTimerResumed(TimerCreated e) {
        logger.info(String.format("[+] (TimerResumed) -> Timer '%s' resumed, which lasted %d sec.", e.getAsyncTimer().getName(), e.getAsyncTimer().getDuration()));
    }

}
