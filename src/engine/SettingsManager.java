package engine;

import buildings.Building;
import buildings.BuildingManager;
import events.EventManager;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;
import transport.Transport;
import transport.TransportManager;

import java.util.ArrayList;

/**
 * Manages settings (initializes managers)
 */
public final class SettingsManager {
    private SettingsManager() {}

    public static void initializeSimulator() {
        ConsumablesPack CP = SettingsStream.loadConsumablesPack();
        UnitsPack UP = SettingsStream.loadUnitsPack();
        ResourcesManager.initializeResourcesManager(CP, UP);

        ArrayList<Building> buildings = SettingsStream.loadBuildings();
        BuildingManager.initializeBuildingManager(buildings);

        ArrayList<Transport> transports = SettingsStream.loadTransports();
        //int transportOrderBreak = SettingsStream.loadTransportOrderBreak();
        TransportManager.initializeResourcesManager(transports, 1);

        int chanceForEvent = SettingsStream.loadChanceForEvent();
        int timeBreakForEvent = SettingsStream.loadTimeBreakForEvent();
        EventManager.initializeEventManager(chanceForEvent, timeBreakForEvent);
    }

}
