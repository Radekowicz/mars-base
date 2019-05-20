package gui;

import buildings.Building;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BuildingPanel extends JPanel {
    private static final int WIDTH = 1024;
    private Building building;
    private JPanel infoPanel;
    private JPanel actionPanel;

    public BuildingPanel(Building building) {
        this.building = building;

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1024, 600));
        add(new JLabel("Building name: "));
        add(new JLabel(building.getName()));
        add(new JLabel("Building status:"));
        add(new JLabel(building.getBuildingStatusAsString()));

        Border innerBorder = BorderFactory.createTitledBorder("Information");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        add(infoPanel);

        actionPanel = new JPanel();

        add(actionPanel);
    }
}

