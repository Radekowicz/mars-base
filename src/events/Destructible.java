package events;

/**
 * Classes which implements the interface have to be destructible in part or whole
 */
public interface Destructible {
    void damage();
    String getName();
}
