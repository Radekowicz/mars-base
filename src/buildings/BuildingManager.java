package buildings;

import events.EventListener;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SuppressWarnings("Duplicates")

public final class BuildingManager {
    private static BuildingManager buildingManager = null;
    private static long hubCapacity = 50;
    private static int numberOfPrintStations;
    private static long maxHumanCapacity;
    private static List<Building> buildings = new ArrayList<>();
    private EventListener eventListener;

    private BuildingManager(List<Building> buildings) {
        this.buildings = buildings;
        Collections.sort(buildings);
    }

    /**
     *sets starting values
     * @param buildings is a list of buildings
     */
    public static void initializeBuildingManager(List<Building> buildings) {
        if (buildingManager == null)
            buildingManager = new BuildingManager(buildings);
        numberOfPrintStations = countPrintStations();
        maxHumanCapacity = countMaxHumanCapacity();
    }

    public static int getNumberOfPrintStations() {
        return numberOfPrintStations;
    }

    public static long getMaxHumanCapacity() {
        return maxHumanCapacity;
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
            if (building.getBuildingStatus() == BuildingStatus.WORKING) {
                if (ResourcesManager.subtract(building.consumeResources())) {
                    ResourcesManager.add(building.generateResources());
                }
                if (building instanceof RobotStation) {
                    ResourcesManager.add(new UnitsPack(0, 1));
                }
            }
            else if (building.getBuildingStatus() == BuildingStatus.IN_BUILD) {
                building.decreaseCounter();
                if (building.isReady()) {
                    building.setBuildingStatus(BuildingStatus.WORKING);
                    building.setChecked(false);
                    numberOfPrintStations++;
                    if (building instanceof Hub) {
                        maxHumanCapacity += hubCapacity;
                    }
                    if (building instanceof PrintStation) {
                        numberOfPrintStations++;
                    }
                }
            }
            else if (building.getBuildingStatus() == BuildingStatus.DESTROYED && !building.isChecked()) {
                buildingsToRemove.add(building);
                if (building instanceof Hub) {
                    maxHumanCapacity -= hubCapacity;
                    building.setChecked(true);
                }
                if (building instanceof PrintStation) {
                    numberOfPrintStations--;
                    building.setChecked(true);
                }
            }
            else if (building.getBuildingStatus() == BuildingStatus.DAMAGED && !building.isChecked()) {
                if (building instanceof Hub) {
                    maxHumanCapacity -= hubCapacity;
                    building.setChecked(true);
                }
                if (building instanceof PrintStation) {
                    numberOfPrintStations--;
                    building.setChecked(true);
                }
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

    public static long countMaxHumanCapacity () {
        int amount = 0;
        for (Building building : buildings) {
            if (building instanceof Hub)
                amount += hubCapacity;
        }
        return amount;
    }

    public static List<Building> getBuildings() {
        return buildings;
    }

    public static int countPrintStations() {
        int amount = 0;
        for (Building building : buildings) {
            if(building instanceof PrintStation) amount++;
        }
        return amount;
    }
}
