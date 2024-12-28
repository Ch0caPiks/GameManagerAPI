package fr.chocapiks.gamemanagerapi.core.events;

import fr.chocapiks.gamemanagerapi.core.Event;
import fr.chocapiks.gamemanagerapi.types.Trigger;

//The event is used to detect when one of the timers with a trigger has been triggered.
public class TriggerRelease extends Event {
    private final Trigger trigger;

    public TriggerRelease(Trigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public int getId() {
        return trigger.getId();
    }

    public int getSecond() {
        return trigger.getSecond();
    }

    public String getName() {
        return trigger.getName();
    }

}
