package gui;

import buildings.Building;
import buildings.BuildingStatus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingPanel extends JPanel {
    private static final int WIDTH = 1024;
    private Building building;
    private JPanel infoPanel;
    private JPanel actionPanel;

    public BuildingPanel(Building building) {
        this.building = building;
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1024, 600));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Building name: "));
        infoPanel.add(new JLabel(building.getName()));
        infoPanel.add(new JLabel("Building status:"));
        infoPanel.add(new JLabel(building.getBuildingStatusAsString()));

        Border innerBorderForInfo = BorderFactory.createTitledBorder("Information");
        Border outerBorderForInfo = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForInfo, innerBorderForInfo));
        add(infoPanel);

        actionPanel = new JPanel();
        if (building.getBuildingStatus() == BuildingStatus.WORKING) {
            JButton stopWorkingButton = new JButton("Turn off");
            stopWorkingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    building.setBuildingStatus(BuildingStatus.CLOSED);
                }
            });
            actionPanel.add(stopWorkingButton);
        }
        else if (building.getBuildingStatus() == BuildingStatus.CLOSED) {
            JButton startWorkingButton = new JButton("Turn on");
            startWorkingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    building.setBuildingStatus(BuildingStatus.WORKING);
                }
            });
            add(startWorkingButton);
        }


        if (building.getBuildingStatus() == BuildingStatus.DAMAGED) {
            JButton fixButton = new JButton("Fix");
            fixButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
//                    if (!building.fix()) {
//                        JOptionPane.showMessageDialog(null, "You need more resources to fix this building");
//                    }
                }
            });
            infoPanel.add(fixButton);
        }


        Border innerBorderForAction = BorderFactory.createTitledBorder("Information");
        Border outerBorderForAction = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        actionPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderForAction, innerBorderForAction));
        add(actionPanel);
    }
}

