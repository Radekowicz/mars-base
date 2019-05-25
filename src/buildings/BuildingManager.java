package buildings;

import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public final class BuildingManager {
    private static BuildingManager buildingManager = null;
    private static long maximalCapacityOfHub = 50;

    private static List<Building> buildings;
    private static List<Building> inBuildBuildings;

    private BuildingManager(List<Building> buildings, List<Building> inBuildBuildings) {
        this.buildings = buildings;
        this.inBuildBuildings = inBuildBuildings;
        Collections.sort(buildings);
    }

    public static void initializeBuildingManager(List<Building> buildings,  List<Building> inBuildBuildings) {
        if (buildingManager == null)
            buildingManager = new BuildingManager(buildings, inBuildBuildings);
    }

    public static boolean canBuild(Building building) {
        if (ResourcesManager.isEnough(building.costOfBuildingInConsumables(), new UnitsPack(0,0))) return true;
        else return false;
    }
    
    public static void update() {
        List<Building> buildingsToRemove = new ArrayList<>();

        for (Building building : inBuildBuildings) {
            building.decreaseCounter();
            if(building.isReady()) {
                buildings.add(building);
                buildingsToRemove.add(building);
            }
        }

        for (Building building : buildingsToRemove) {
            inBuildBuildings.remove(building);
        }

        buildingsToRemove.removeAll(buildingsToRemove);

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

    public static boolean addBuilding(Building building) {
        if (canBuild(building)) {
            inBuildBuildings.add(building);
            ResourcesManager.subtract(building.costOfBuildingInConsumables());
            return true;
        }

        return false;
    }

    public static long getMaxHumanCapacity () {
        int amount = 0;
        for (Building building : buildings) {
            if (building instanceof Hub) amount++;
        }
        return amount * maximalCapacityOfHub;
    }


    public static List<Building> getBuildings() {
        return buildings;
    }
}
