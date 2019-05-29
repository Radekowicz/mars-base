package gui;

/**
 * Generic interface which listens adding new struct like building or mean of transport
 * @param <S> Building or Transport
 */
public interface NewItemListener<S> {
    void newItemAdded(S item);
}
