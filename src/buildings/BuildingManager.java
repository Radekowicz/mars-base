package buildings;

import resources.ConsumablesPack;
import buildings.Building;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BuildingManager {
    private static BuildingManager buildingManager = null;

    ConsumablesPack ConsumablesPack;
    private static List<Building> buildings;
    private static List<Building> awaitingBuildings;

    private BuildingManager(ConsumablesPack ConsumablesPack, List<Building> buildings) {
        this.ConsumablesPack = ConsumablesPack;
        this.buildings = buildings;
        this.awaitingBuildings = new ArrayList<>();
        Collections.sort(buildings);
    }

    public static BuildingManager initializeBuildingManager(ConsumablesPack ConsumablesPack, List<Building> buildings) {
        if (buildingManager == null)
            buildingManager = new BuildingManager(ConsumablesPack, buildings);
        return buildingManager;
    }


    public static boolean build(Building building) {
        if(ResourcesManager.isEnough(building.costOfBuildingInConsumables(), new UnitsPack(0,0))) return true;
        else return false;
    }
    
    public static void update() {
        //resource management

        for (Building building : buildings) {
            if(build(building)) {
                ResourcesManager.subtract(building.generateResources(), new UnitsPack(0,0));
                ResourcesManager.add(building.generateResources(), new UnitsPack(0,0));
            }
        }

        //awaiting building management
        List<Building> toRemove = new ArrayList<>();
        for (Building building : awaitingBuildings) {
            building.decreaseCounter();
            if (building.isReady()) {
                buildings.add(building);
                toRemove.add(building);
            }
        }
        awaitingBuildings.removeAll(toRemove);
    }

    public static List<Building> getBuildings() {
        return buildings;
    }
}
