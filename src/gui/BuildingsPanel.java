package gui;

import buildings.Building;
import buildings.BuildingManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuildingsPanel extends JPanel  {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 34;

    private List<BuildingButton> buildingsButtons;
    private JButton addBuildingButton;
    JPanel buildingsPanel;
    private BuildingsPanelListener buildingsPanelListener;
    private AddBuildingListener addBuildingListener;

    public BuildingsPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buildingsButtons = new ArrayList<>();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        buildingsPanel = new JPanel();

        for (Building building: BuildingManager.getBuildings()) {
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
            buildingsPanel.add(buildingButton);
        }

        add(buildingsPanel);

        addBuildingButton = new JButton("Add building");
        addBuildingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                AddBuildingButtonEvent addBuildingButtonEvent = new AddBuildingButtonEvent(event, new NewItemListener() {
                    @Override
                    public void newItemAdded(Object item) {
                        if (item != null)
                            refresh((Building) item);
                    }
                });


                if (addBuildingButtonEvent != null) {
                    addBuildingListener.AddBuildingButtonOccurred(addBuildingButtonEvent);
                }

            }
        });

        add(addBuildingButton);
    }

    public void refresh(Building building) {
        if (building == null)
            return;

        BuildingButton buildingButton = new BuildingButton(building);
        buildingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                BuildingButtonEvent buildingButtonEvent = new BuildingButtonEvent(event, buildingButton.getBuilding());

                if (buildingButtonEvent != null) {
                    buildingsPanelListener.BuildingPanelOccurred(buildingButtonEvent);
                }
            }
        });
        buildingsPanel.add(buildingButton);
        buildingsPanel.validate();
        buildingsPanel.repaint();
    }

    public void setBuildingsPanelListener(BuildingsPanelListener buildingPanelListener) {
        this.buildingsPanelListener = buildingPanelListener;
    }

    public void setAddBuildingListener(AddBuildingListener addBuildingListener) {
        this.addBuildingListener = addBuildingListener;
    }
}