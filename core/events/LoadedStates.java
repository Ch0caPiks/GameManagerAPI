package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.types.State;

import java.util.*;
import java.util.Set;

public class LoadedStates extends Event {

    private final Set<State> states;

    public LoadedStates(Set<State> states) {
        this.states = GameManagerApi.getInstance().game.getStates();
    }

    public Set<State> getStates() {
        return states;
    }

    public State getFirst() {
        return states.stream().min(Comparator.comparingInt(State::getId)).orElse(null);
    }

    public State getLast() {
        return states.stream().max(Comparator.comparingInt(State::getId)).orElse(null);
    }

}
