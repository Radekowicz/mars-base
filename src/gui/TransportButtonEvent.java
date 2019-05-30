package gui;

import transport.Transport;

import java.util.EventObject;

/**
 * Event object that informs the main panel about the change of the action panel
 */
public class TransportButtonEvent extends EventObject {
    private TransportPanel transportPanel;

    public TransportButtonEvent(Object source) {
        super(source);
    }

    public TransportButtonEvent(Object source, Transport transport) {
        super(source);
        transportPanel = new TransportPanel(transport);
    }

    public TransportPanel getPanel() {
        return transportPanel;
    }
}
