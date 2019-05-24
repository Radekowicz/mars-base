package events;

public abstract class Event implements DescriptionPublisher {
    private EventListener eventListener;

    public Event(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute() {
        eventListener.eventOccurred(this);
    }
}
