package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.utils.AsyncTimer;

public class TimerCanceled extends Event {

    private final AsyncTimer asyncTimer;

    public TimerCanceled(AsyncTimer asyncTimer) {
        this.asyncTimer = asyncTimer;
    }

    public AsyncTimer getAsyncTimer() {
        return asyncTimer;
    }

}
