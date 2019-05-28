package events;

public abstract class Event implements DescriptionPublisher {
    private EventListener eventListener;
    private String describe;

    /**
     * Sets event listener
     * @param eventListener EventListener
     */
    public Event(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * Runs the event process
     */
    public void execute() {
        eventListener.eventOccurred(this);
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String getDescribed() {
        return describe;
    }
}
