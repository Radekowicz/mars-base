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

    private BuildingManager(ConsumablesPack ConsumablesPack, List<Building> buildings) {
        this.ConsumablesPack = ConsumablesPack;
        this.buildings = buildings;
        Collections.sort(buildings);
    }

    public static void initializeBuildingManager(ConsumablesPack ConsumablesPack, List<Building> buildings) {
        if (buildingManager == null)
            buildingManager = new BuildingManager(ConsumablesPack, buildings);
    }

    public static boolean build(Building building) {
        if (ResourcesManager.isEnough(building.costOfBuildingInConsumables(), new UnitsPack(0,0))) return true;
        else return false;
    }
    
    public static void update() {
        List<Building> buildingsToRemove = new ArrayList<>();

        for (Building building : buildings) {
            if (building.getBuildingStatus() == BuildingStatus.WORKING) {
                if(ResourcesManager.subtract(building.consumeResources())) {
                    ResourcesManager.add(building.generateResources());
                }
            }
            else if (building.getBuildingStatus() == BuildingStatus.DESTROYED) {
                buildingsToRemove.add(building);
            }
        }

        for (Building building : buildingsToRemove) {
            buildings.remove(building);
        }
        buildingsToRemove.removeAll(buildingsToRemove);

    }


    public static List<Building> getBuildings() {
        return buildings;
    }
}
