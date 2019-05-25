package gui;

import java.util.EventObject;

public class OrderTransportButtonEvent extends EventObject {
    public OrderTransportButtonEvent(Object source) {
        super(source);
    }

    public OrderTransportPanel getPanel() {
        return new OrderTransportPanel();
    }
}
