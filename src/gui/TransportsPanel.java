package gui;

import transport.Transport;
import transport.TransportManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TransportsPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 34;

    private List<TransportButton> transportsButtons;
    private JButton orderTransportButton;
    private TransportsPanelListener transportsPanelListener;

    public TransportsPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        transportsButtons = new ArrayList<>();

        for (Transport transport: TransportManager.getTransports()) {
            TransportButton transportButton = new TransportButton(transport);
            transportsButtons.add(transportButton);
            transportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TransportButtonEvent transportButtonEvent = new TransportButtonEvent(event, transportButton.getTransport());

                    if (transportButtonEvent != null) {
                        transportsPanelListener.TransportPanelOccurred(transportButtonEvent);
                    }
                }
            });
            add(transportButton);
        }

        orderTransportButton = new JButton("Order transport");
        orderTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });

        add(orderTransportButton);
    }

    public void setTransportsPanelListener(TransportsPanelListener transportsPanelListener) {
        this.transportsPanelListener = transportsPanelListener;
    }

}
