package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.types.Trigger;
import fr.chocapiks.gamemanagerapi.utils.AsyncTimer;

import java.util.List;

public class TimerStarted extends Event {

    private final AsyncTimer asyncTimer;

    public TimerStarted(AsyncTimer asyncTimer) {
        this.asyncTimer = asyncTimer;
    }

    public AsyncTimer getAsyncTimer() {
        return asyncTimer;
    }

    public List<Trigger> getTriggers() {
        return asyncTimer.getTriggers();
    }

    public void cancelled() {
        asyncTimer.cancel();
    }

}
