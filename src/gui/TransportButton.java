package gui;

import transport.Transport;

import javax.swing.*;

public class TransportButton extends JButton {
    Transport transport;

    public TransportButton(Transport transport) {
        super(transport.getName());
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }
}
