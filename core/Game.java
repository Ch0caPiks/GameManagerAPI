package fr.chocapiks.gamemanagerapi.core;

import fr.chocapiks.gamemanagerapi.core.events.AddPlayer;
import fr.chocapiks.gamemanagerapi.core.events.EndGame;
import fr.chocapiks.gamemanagerapi.core.events.RemovePlayer;
import fr.chocapiks.gamemanagerapi.core.events.StartGame;
import fr.chocapiks.gamemanagerapi.types.State;
import fr.chocapiks.gamemanagerapi.utils.UStatus;

import java.util.HashSet;
import java.util.Set;

public class Game<P> {
    private int playerMax; //Maximum number of players
    private int playerMinStart; //Minimum number of players to start a game -> waitMaxDuration
    private int playerSufficientStart; //Minimum number of players to start a game -> waitSufPlayerDuration
    private int gameDuration; //How long does a game last (in seconds)
    private int waitMaxDuration; //Maximum waiting time after reaching player minimum (in seconds)
    private int waitSufPlayerDuration; //Waiting time when the number of players required for a game has been reached (in seconds)
    private Set<State> lStatus = new HashSet<>();
    private State state; //Actual State
    private final Set<P> players = new HashSet<>();
    private static final EventManager eventManager = EventManager.getInstance();

    public Game(int playerMax, int playerMinStart, int playerSufficientStart, int gameDuration, int waitMaxDuration, int waitSufPlayerDuration) {
        this.playerMax = playerMax;
        this.playerMinStart = playerMinStart;
        this.playerSufficientStart = playerSufficientStart;
        this.gameDuration = gameDuration;
        this.waitMaxDuration = waitMaxDuration;
        this.waitSufPlayerDuration = waitSufPlayerDuration;
    }

    public Set<State> getStates() {
        return lStatus;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(int playerMax) {
        this.playerMax = playerMax;
    }

    public int getPlayerMinStart() {
        return playerMinStart;
    }

    public void setPlayerMinStart(int playerMinStart) {
        this.playerMinStart = playerMinStart;
    }

    public int getPlayerSufficientStart() {
        return playerSufficientStart;
    }

    public void setPlayerSufficientStart(int playerSufficientStart) {
        this.playerSufficientStart = playerSufficientStart;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public int getWaitMaxDuration() {
        return waitMaxDuration;
    }

    public void setWaitMaxDuration(int waitMaxDuration) {
        this.waitMaxDuration = waitMaxDuration;
    }

    public int getWaitSufPlayerDuration() {
        return waitSufPlayerDuration;
    }

    public void setWaitSufPlayerDuration(int waitSufPlayerDuration) {
        this.waitSufPlayerDuration = waitSufPlayerDuration;
    }

    // Check if connected players > 0
    public boolean isEmpty() {
        return players.isEmpty();
    }

    public void setlStatus(Set<State> lStatus) {
        if (lStatus.isEmpty() || this.lStatus == lStatus) return;
        this.lStatus = lStatus;
    }

    public Set<P> getPlayers() {
        return players;
    }

    public void addPlayers(P player) {
        if (players.contains(player)) return;
        players.add(player);
        AddPlayer<?> event = new AddPlayer<>(player);
        eventManager.callEvent(event);
    }

    public void removePlayers(P player) {
        if (!players.contains(player)) return;
        players.remove(player);
        RemovePlayer<?> event = new RemovePlayer<>(player);
        eventManager.callEvent(event);
    }

    public void clearPlayers() {
        if (players.isEmpty()) return;
        players.clear();
    }

    public void startGame() {
        if(UStatus.isStatusEmpty()) return;
        setState(UStatus.getFirst());
        StartGame event = new StartGame();
        eventManager.callEvent(event);
    }

    public void endGame() {
        if(UStatus.isStatusEmpty()) return;
        setState(UStatus.getLast());
        EndGame event = new EndGame();
        eventManager.callEvent(event);
    }
}
