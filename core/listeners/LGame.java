package fr.chocapiks.gamemanagerapi.core.listeners;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.GEventHandler;
import fr.chocapiks.gamemanagerapi.core.GEventListener;
import fr.chocapiks.gamemanagerapi.core.events.AddPlayer;
import fr.chocapiks.gamemanagerapi.core.events.EndGame;
import fr.chocapiks.gamemanagerapi.core.events.RemovePlayer;
import fr.chocapiks.gamemanagerapi.core.events.StartGame;

import java.util.logging.Logger;

public class LGame implements GEventListener {

    private final static Logger logger = GameManagerApi.getInstance().logger;

    @GEventHandler
    public void onAddPlayer(AddPlayer<?> e) { //Log when player was add
        logger.info(String.format("[+] (AddPlayer) -> Player: '%s'\n", e.getPlayer().toString()));
    }

    @GEventHandler
    public void onRemovePlayer(RemovePlayer<?> e) { //Log when player was removed
        logger.info(String.format("[+] (RemovePlayer) -> Player: '%s'\n", e.getPlayer().toString()));
    }

    @GEventHandler
    public void onStart(StartGame e) { //Log Game Start
        StringBuilder result = new StringBuilder();
        result.append("[+] (StartGame) -> Game Started\n");
        result.append(String.format("[+] (StartGame) {Settings} -> MinPlayer: %d, SufficientPlayer: %d, MaxPlayer: %d, \n", e.getGame().getPlayerMinStart(), e.getGame().getPlayerSufficientStart(), e.getGame().getPlayerMax()));
        result.append(String.format("[+] (StartGame) {Duration} -> WaitSufficientDuration: %d, WaitMaxDuration: %d, GameDuration: %d, \n", e.getGame().getWaitSufPlayerDuration(), e.getGame().getWaitMaxDuration(), e.getGame().getGameDuration()));
        e.getGame().getStates().forEach(state -> result.append(String.format("[+] (StartGame) {States} -> ID: %d, Name: '%s'\n", state.getId(), state.getName())));
        logger.info(result.toString());
    }

    @GEventHandler
    public void onEnd(EndGame e) {
        logger.info("[+] (EndGame) -> Game Ended \n");
    }

}
