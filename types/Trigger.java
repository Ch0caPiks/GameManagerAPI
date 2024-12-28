package fr.chocapiks.gamemanagerapi.types;

import fr.chocapiks.gamemanagerapi.utils.AsyncTimer;

public class Trigger {
    private final int second;
    private final int triggerId;
    private final String name;
    private final AsyncTimer asyncTimer;

    public Trigger(AsyncTimer asyncTimer, int second, int triggerId, String name) {
        this.second = second;
        this.triggerId = triggerId;
        this.name = name;
        this.asyncTimer = asyncTimer;
    }

    public int getSecond() {
        return second;
    }

    public int getId() {
        return triggerId;
    }

    public String getName() {
        return name;
    }

    public AsyncTimer getAsyncTimer() {
        return asyncTimer;
    }
}