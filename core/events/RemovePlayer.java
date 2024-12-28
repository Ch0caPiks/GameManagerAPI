package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;

public class RemovePlayer<P> extends Event {

    private final P player;

    public RemovePlayer(P player) {
        this.player = player;
    }

    public P getPlayer() {
        return player;
    }

}
