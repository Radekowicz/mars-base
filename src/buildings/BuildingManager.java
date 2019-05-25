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
    private static int numberOfPrintStations;
    private static List<Building> buildings;

    private BuildingManager(List<Building> buildings) {
        this.buildings = buildings;
        Collections.sort(buildings);
    }

    public static void initializeBuildingManager(List<Building> buildings) {
        if (buildingManager == null)
            buildingManager = new BuildingManager(buildings);
        numberOfPrintStations = howManyPrintStations();
    }

    public static boolean canBuild(Building building) {
        if (ResourcesManager.isEnough(building.costOfBuildingInConsumables()) && ResourcesManager.isEnough(building.costOfBuildingInUnits()) && numberOfPrintStations > 0) {
            numberOfPrintStations--;
            return true;
        }
        else return false;
    }
    
    public static void update() {
        List<Building> buildingsToRemove = new ArrayList<>();

        for (Building building : buildings) {
            if (building.getBuildingStatus() == BuildingStatus.IN_BUILD) {
                building.decreaseCounter();
                if (building.isReady()) {
                    building.setBuildingStatus(BuildingStatus.WORKING);
                    numberOfPrintStations++;
                }
            }
        }

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
            buildings.add(building);
            building.setBuildingStatus(BuildingStatus.IN_BUILD);
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

    public static int howManyPrintStations() {
        int amount = 0;
        for (Building building : buildings) {
            if(building instanceof PrintStation) amount++;
        }
        return  amount;
    }


}
