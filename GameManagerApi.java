package fr.chocapiks.gamemanagerapi;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.core.EventManager;
import fr.chocapiks.gamemanagerapi.core.GEventListener;
import fr.chocapiks.gamemanagerapi.core.Game;
import fr.chocapiks.gamemanagerapi.types.State;
import fr.chocapiks.gamemanagerapi.utils.UStatus;

import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.logging.Logger;
import org.reflections.Reflections;

public final class GameManagerApi<P> {
    public final Logger logger = Logger.getLogger("GMA");
    public final Game<P> game;
    public static GameManagerApi<?> instance;
    private final EventManager eventManager = EventManager.getInstance();
    public GameManagerApi(Game<P> game, Set<State> states) {
        if (game == null || states == null) {
            throw new IllegalArgumentException("Game & States cannot be null");
        }
        this.game = game;
        UStatus.loadStates(states);
        registerAll("fr.chocapiks.gamemanagerapi.core.events", Event.class);
        registerAll("fr.chocapiks.gamemanagerapi.core.listeners", GEventListener.class);
    }

    public static GameManagerApi<?> getInstance() {
        return instance;
    }

    private <T> void registerAll(String packageName, Class<T> type) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(type);

        for (Class<? extends T> clazz : classes) {
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                try {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    if (instance instanceof GEventListener) {
                        eventManager.registerListener((GEventListener) instance);
                    }
                } catch (Exception e) {
                    logger.severe("Failed to register: " + clazz.getName() + "\n" + e);
                }
            }
        }
    }

}
