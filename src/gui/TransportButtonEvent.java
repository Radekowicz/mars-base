package gui;

import transport.Transport;

import java.util.EventObject;

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
