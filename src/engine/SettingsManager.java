package engine;

import buildings.Building;
import buildings.BuildingManager;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.List;

public final class SettingsManager {
    private SettingsManager() {}

    public static void initializeSimulator() {
        ConsumablesPack CP = SettingsStream.loadConsumablesPack();
        UnitsPack UP = SettingsStream.loadUnitsPack();
        ResourcesManager.initializeResourcesManager(CP, UP);

        ArrayList<Building> buildings = SettingsStream.loadBuildings();
        BuildingManager.initializeBuildingManager(CP, buildings);
    }

}
