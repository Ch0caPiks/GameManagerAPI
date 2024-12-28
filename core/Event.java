package fr.chocapiks.gamemanagerapi.core;

public abstract class Event {
    private boolean cancelled = false;

    // Allows the event to be canceleda
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    // Checks if the event is canceled
    public boolean isCancelled() {
        return cancelled;
    }
}
