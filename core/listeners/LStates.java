package fr.chocapiks.gamemanagerapi.core.listeners;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.GEventHandler;
import fr.chocapiks.gamemanagerapi.core.events.ChangedState;
import fr.chocapiks.gamemanagerapi.core.events.LoadedStates;
import fr.chocapiks.gamemanagerapi.core.GEventListener;

import java.util.logging.Logger;

public class LStates implements GEventListener {

    private final static Logger logger = GameManagerApi.getInstance().logger;

    @GEventHandler //Logs Status Loaded
    public void onLoad(LoadedStates e) {
        StringBuilder result = new StringBuilder();
        e.getStates().forEach(state -> result.append(String.format("[+] (LoadStates) -> ID: %d, Name: '%s'\n", state.getId(), state.getName())));
        logger.info(result.toString());
    }

    @GEventHandler //Log when state was updated
    public void onChanged(ChangedState e) {
        logger.info(String.format("[+] (ChangedState) -> (Previous) ID: %d, Name: '%s'\n", e.getPreviousState().getId(), e.getPreviousState().getName()));
        logger.info(String.format("[+] (ChangedState) -> (Previous) ID: %d, Name: '%s'\n", e.getState().getId(), e.getState().getName()));
    }

}
