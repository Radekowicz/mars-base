package gui;

import transport.Transport;
import transport.TransportManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")

public class TransportsPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 34;

    private List<TransportButton> transportsButtons;
    private JButton orderTransportButton;
    private TransportsPanelListener transportsPanelListener;
    private OrderTransportListener orderTransportListener;
    private JPanel transportContener;

    public TransportsPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        transportsButtons = new ArrayList<>();
        transportContener = new JPanel();

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
            transportContener.add(transportButton);
        }

        add(transportContener);

        orderTransportButton = new JButton("Order transport");
        orderTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                OrderTransportButtonEvent orderTransportButtonEvent = new OrderTransportButtonEvent(event, new NewItemListener() {
                    @Override
                    public void newItemAdded(Object item) {
                        if (item != null)
                            refresh((Transport) item);
                    }
                });

                if (orderTransportButtonEvent != null) {
                    orderTransportListener.orderTransportButtonOccurred(orderTransportButtonEvent);
                }
            }
        });

        add(orderTransportButton);
    }

    public void setTransportsPanelListener(TransportsPanelListener transportsPanelListener) {
        this.transportsPanelListener = transportsPanelListener;
    }

    public void setOrderTransportListener(OrderTransportListener orderTransportListener) {
        this.orderTransportListener = orderTransportListener;
    }

    void refresh(Transport transport) {
        if (transport == null)
            return;

        TransportButton transportButton = new TransportButton(transport);
        transportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                TransportButtonEvent transportButtonEvent = new TransportButtonEvent(event, transportButton.getTransport());

                if (transportButtonEvent != null) {
                    transportsPanelListener.TransportPanelOccurred(transportButtonEvent);
                }
            }
        });
        transportContener.add(transportButton);
    }

    public void refreshAll() {
        transportContener.removeAll();

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
            transportContener.add(transportButton);
        }

        transportContener.validate();
        transportContener.repaint();
    }
}
