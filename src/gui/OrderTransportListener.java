package gui;

import java.util.EventListener;

public interface OrderTransportListener extends EventListener {
    void orderTransportButtonOccurred(OrderTransportButtonEvent event);
}
