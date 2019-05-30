package gui;

import transport.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action panel, shows info about transport order
 */
public class OrderTransportPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 200;
    private JComboBox transportsComboBox;
    private JButton orderButton;
    private JPanel resultInfoPanel;
    private NewItemListener newItemListener;
    Transport transport;

    public OrderTransportPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        transportsComboBox = new JComboBox();
        transportsComboBox.setPreferredSize(new Dimension(50, 30));
        transportsComboBox.addItem("Truck");
        transportsComboBox.addItem("Swift Rocket");
        transportsComboBox.addItem("BFRocket");

        add(transportsComboBox);

        orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;

                switch (transportsComboBox.getSelectedIndex()) {
                    case 0:
                        transport = new Truck();
                        result = TransportManager.orderTransport(transport);
                        break;
                    case 1:
                        transport = new SwiftRocket();
                        result = TransportManager.orderTransport(transport);
                        break;
                    case 2:
                        transport = new BFRocket();
                        result = TransportManager.orderTransport(transport);
                        break;
                    default:
                        break;
                }


                resultInfoPanel.removeAll();
                String info;

                if (result) {
                    info = "New transport is one the way";
                    newItemListener.newItemAdded(transport);
                }
                else {
                    info = "You cannot order transport, not enough time has passed from last order";
                }

                resultInfoPanel.add(new JLabel(info));
                resultInfoPanel.validate();
                resultInfoPanel.repaint();
            }
        });

        add(orderButton);

        resultInfoPanel = new JPanel();
        add(resultInfoPanel);
    }

    public void setNewItemListener(NewItemListener newItemListener) {
        this.newItemListener = newItemListener;
    }
}
