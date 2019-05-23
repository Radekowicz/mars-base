package events;

public abstract class Event {
    private EventListener eventListener;

    public Event(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute() {
        eventListener.eventOccurred(this);
    }

    public abstract String getDescribed();
}
