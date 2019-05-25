package gui;

import buildings.Building;

import java.util.EventObject;

public class BuildingButtonEvent extends EventObject {

    private BuildingPanel buildingPanel;

    public BuildingButtonEvent(Object source) {
        super(source);
    }

    public BuildingButtonEvent(Object source, Building building) {
        super(source);
        buildingPanel = new BuildingPanel(building);
    }

    public BuildingPanel getPanel() {
        return buildingPanel;
    }
}