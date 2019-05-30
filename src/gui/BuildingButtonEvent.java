package gui;

import buildings.Building;

import java.util.EventObject;

/**
 * Event object that informs the main panel about the change of the action panel
 */
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