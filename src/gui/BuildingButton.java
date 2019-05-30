package gui;

import buildings.Building;

import javax.swing.*;

/**
 * Special button that stores the reference to building
 */
public class BuildingButton extends JButton {
    Building building;

    public BuildingButton(Building building) {
        super(building.getName());
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
