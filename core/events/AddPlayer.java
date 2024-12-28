package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;

public class AddPlayer<P> extends Event {

    private final P player;

    public AddPlayer(P player) {
        this.player = player;
    }

    public P getPlayer() {
        return player;
    }

}
