package gui;

import transport.TransportManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderTransportPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 200;
    private JComboBox transportsComboBox;
    private JButton orderButton;
    private JPanel resultInfoPanel;

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
                        result = TransportManager.orderTransport("Truck");
                        break;
                    case 1:
                        result = TransportManager.orderTransport("SwiftRocket");
                        break;
                    case 2:
                        result = TransportManager.orderTransport("BFRocket");
                        break;
                    default:
                        break;
                }


                resultInfoPanel.removeAll();
                String info = result ? "New transport is one the way." : "You cannot order transport, not enough time has passed from last order";

//                if ()

                resultInfoPanel.add(new JLabel(info));
                resultInfoPanel.validate();
                resultInfoPanel.repaint();

            }
        });

        add(orderButton);

        resultInfoPanel = new JPanel();
        add(resultInfoPanel);
    }
}
