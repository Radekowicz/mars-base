package gui;

import resources.ResourcesManager;
import transport.Place;
import transport.Transport;
import transport.TransportManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransportPanel extends JPanel {
    private Transport transport;
    private JPanel infoPanel;
    private JPanel actionPanel;

    public TransportPanel(Transport transport) {
        this.transport = transport;
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Transport name: "));
        infoPanel.add(new JLabel(transport.getName()));
        infoPanel.add(new JLabel("Transport status:"));
        infoPanel.add(new JLabel(transport.getTransportStatusAsString()));

        Border innerBorderForInfo = BorderFactory.createTitledBorder("Information");
        Border outerBorderForInfo = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForInfo, innerBorderForInfo));
        add(infoPanel);

        actionPanel = new JPanel();

        JComboBox possibleTargets = new JComboBox();

        for (String target: transport.getPossibleTargetsAsStringList())
            possibleTargets.addItem(target);

        JPanel infoPanel = new JPanel();

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String target = possibleTargets.getSelectedItem().toString();
                boolean result = transport.send(Place.getPlaceFromString(target), TransportManager.determineDistance(Place.getPlaceFromString(target)));
                String info = result ? transport.getName() + " have been sent to " + target : "You cannot send " + transport.getName();
                infoPanel.removeAll();
                infoPanel.add(new JLabel(info));
                infoPanel.validate();
                infoPanel.repaint();
            }
        });

        Border innerBorderForAction = BorderFactory.createTitledBorder("Action");
        Border outerBorderForAction = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        actionPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForAction, innerBorderForAction));
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(possibleTargets);
        actionPanel.add(sendButton);
        actionPanel.add(infoPanel);

        add(actionPanel);
    }
}

