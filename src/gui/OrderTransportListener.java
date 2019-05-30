package gui;

import java.util.EventListener;

/**
 * Listens to pressing OrderTransportButton
 */
public interface OrderTransportListener extends EventListener {
    void orderTransportButtonOccurred(OrderTransportButtonEvent event);
}
