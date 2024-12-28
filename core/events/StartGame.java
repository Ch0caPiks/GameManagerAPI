package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.GameManagerApi;
import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.core.Game;

public class StartGame extends Event {

    Game<?> game = GameManagerApi.getInstance().game;

    public StartGame() {}

    public Game<?> getGame() {
        return game;
    }

}
