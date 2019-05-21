package gui;

import transport.Transport;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import buildings.Building;
import buildings.BuildingStatus;

public class TransportPanel extends JPanel {
    private static final int WIDTH = 1024;
    private Transport transport;
    private JPanel infoPanel;
    private JPanel actionPanel;

    public TransportPanel(Transport transport) {
        this.transport = transport;
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Building name: "));
        infoPanel.add(new JLabel(transport.getName()));
        infoPanel.add(new JLabel("Building status:"));
        infoPanel.add(new JLabel(transport.getTransportStatusAsString()));

        Border innerBorderForInfo = BorderFactory.createTitledBorder("Information");
        Border outerBorderForInfo = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForInfo, innerBorderForInfo));
        add(infoPanel);

        actionPanel = new JPanel();


        Border innerBorderForAction = BorderFactory.createTitledBorder("Action");
        Border outerBorderForAction = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        actionPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForAction, innerBorderForAction));
        add(actionPanel);
    }
}

