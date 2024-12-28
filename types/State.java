package fr.chocapiks.gamemanagerapi.types;

public class State {
    private final int id; //State position according to others.
    private String name; //State name
    private int duration; //Do not use it if the system is done on your side. The duration of the status must be less than the duration of the following status. All statuses must be equal to or less than the maximum duration of the game. (In seconds)

    public State(int id, String nameState, int durationState) {
        this.id = id;
        this.name = nameState;
        this.duration = durationState;
    }

    public State(int id, String nameState) {
        this.id = id;
        this.name = nameState;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
