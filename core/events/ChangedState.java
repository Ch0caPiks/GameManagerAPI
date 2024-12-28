package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.types.State;

public class ChangedState extends Event {

    private final State oldstate;
    private final State newstate;

    public ChangedState(State oldstate, State newstate) {
        this.oldstate = oldstate;
        this.newstate = newstate;
    }

    public State getPreviousState() {
        return oldstate;
    }

    public State getState() {
        return newstate;
    }

}