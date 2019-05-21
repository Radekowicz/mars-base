package gui;

import buildings.Building;
import buildings.BuildingManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuildingsPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 34;

    private List<BuildingButton> buildingsButtons;
    private JButton addBuildingButton;
    private BuildingsPanelListener buildingsPanelListener;

    public BuildingsPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buildingsButtons = new ArrayList<>();

        for (Building building: BuildingManager.getBuildings()) {
            System.out.println(building.getName());
            System.out.println(building.toString());
            BuildingButton buildingButton = new BuildingButton(building);
            buildingsButtons.add(buildingButton);
            buildingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    BuildingButtonEvent buildingButtonEvent = new BuildingButtonEvent(event, buildingButton.getBuilding());

                    if (buildingButtonEvent != null) {
                        buildingsPanelListener.BuildingPanelOccurred(buildingButtonEvent);
                    }
                }
            });
            add(buildingButton);
        }

        addBuildingButton = new JButton("Add building");
        addBuildingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });

        add(addBuildingButton);
    }

    public void setBuildingsPanelListener(BuildingsPanelListener buildingPanelListener) {
        this.buildingsPanelListener = buildingPanelListener;
    }
}