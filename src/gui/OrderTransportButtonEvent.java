package gui;

import java.util.EventObject;

/**
 * Event object that informs the main panel about the change of the action panel
 */
public class OrderTransportButtonEvent extends EventObject {
    NewItemListener newItemListener;

    public OrderTransportButtonEvent(Object source) {
        super(source);
    }

    public OrderTransportButtonEvent(Object source, NewItemListener newItemListener) {
        super(source);
        this.newItemListener = newItemListener;
    }

    public OrderTransportPanel getPanel() {
        OrderTransportPanel orderTransportPanel = new OrderTransportPanel();
        orderTransportPanel.setNewItemListener(newItemListener);
        return orderTransportPanel;
    }
}
