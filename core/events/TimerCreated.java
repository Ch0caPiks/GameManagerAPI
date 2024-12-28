package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.utils.AsyncTimer;

public class TimerCreated extends Event {

    private final AsyncTimer asyncTimer;

    public TimerCreated(AsyncTimer asyncTimer) {
        this.asyncTimer = asyncTimer;
    }

    public AsyncTimer getAsyncTimer() {
        return asyncTimer;
    }

    public void cancel() {
        asyncTimer.cancel();
    }

}
