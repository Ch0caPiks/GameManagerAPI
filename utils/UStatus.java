package fr.chocapiks.gamemanagerapi.utils;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.EventManager;
import fr.chocapiks.gamemanagerapi.core.events.ChangedState;
import fr.chocapiks.gamemanagerapi.core.events.LoadedStates;
import fr.chocapiks.gamemanagerapi.types.State;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class UStatus {

    private final static Logger logger = GameManagerApi.getInstance().logger;
    private static final EventManager eventManager = EventManager.getInstance();
    private final static Set<State> vs = GameManagerApi.getInstance().game.getStates();

    // Check that all states are different, then load them
    public static void loadStates(Set<State> sList) {
        if (vs.isEmpty() || sList.isEmpty()) logger.severe("Loading States Error");

        // Sets to track already encountered IDs and names
        Set<String> seenNames = new HashSet<>();
        Set<Integer> seenIds = new HashSet<>();

        sList.stream().filter(state -> {
            // Check if the ID has already been used
            if (seenIds.contains(state.getId())) {
                logger.severe("Error: ID " + state.getId() + " has already been used.");
                return false;
            }
            // Check if the name has already been used
            if (seenNames.contains(state.getName())) {
                logger.severe("Error: Name '" + state.getName() + "' has already been used.");
                return false;
            }
            // Add the ID and name to the sets for tracking
            seenIds.add(state.getId());
            seenNames.add(state.getName());
            return true;
        }).forEach(vs::add);

        // Trigger LoadStatesEvent
        LoadedStates event = new LoadedStates(sList);
        eventManager.callEvent(event);
    }

    public void changeStates(State newState) {
        GameManagerApi<?> s = GameManagerApi.getInstance();
        State oldState = s.game.getState();
        if(newState == oldState) return;
        s.game.setState(newState);
        ChangedState event = new ChangedState(oldState, newState);
        eventManager.callEvent(event);
    }

    public State getState(int id) {
        return vs.stream().filter(state -> state.getId() == id).findFirst().orElse(null);
    }

    public State getState(String name) {
        return vs.stream().filter(state -> Objects.equals(state.getName(), name)).findFirst().orElse(null);
    }

    public static State getFirst() {
        return vs.stream().min(Comparator.comparingInt(State::getId)).orElse(null);
    }

    public static State getLast() {
        return vs.stream().max(Comparator.comparingInt(State::getId)).orElse(null);
    }

    public static boolean isStatusEmpty() {
        return vs.isEmpty();
    }

}
