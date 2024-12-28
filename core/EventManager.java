package fr.chocapiks.gamemanagerapi.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<Class<? extends Event>, List<GEventListener>> listeners = new HashMap<>();

    private static final EventManager instance = new EventManager();

    private EventManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");    }

    public static EventManager getInstance() {
        return instance;
    }

    // Registers a listener
    public void registerListener(GEventListener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(GEventHandler.class)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1 && Event.class.isAssignableFrom(params[0])) {
                    Class<? extends Event> eventClass = params[0].asSubclass(Event.class);
                    listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
                }
            }
        }
    }

    // Triggers an event
    public <T extends Event> void callEvent(T event) {
        List<GEventListener> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (GEventListener listener : eventListeners) {
                for (Method method : listener.getClass().getMethods()) {
                    if (method.isAnnotationPresent(GEventHandler.class)) {
                        Class<?>[] params = method.getParameterTypes();
                        if (params.length == 1 && params[0].isAssignableFrom(event.getClass())) {
                            try {
                                method.invoke(listener, event);
                                if (event.isCancelled()) {
                                    return; // If cancelled, stop propagation
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e);
                            }
                        }
                    }
                }
            }
        }
    }
}
