package buildings;

import events.Destructible;
import events.Fixable;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.Random;

public abstract class Building implements Comparable<Building>, Destructible, Fixable {
    private int counter = timeOfBuild();
    private BuildingStatus buildingStatus;
    private String name;

    public Building(String name) {
        this.name = name;
        buildingStatus = BuildingStatus.WORKING;
    }

    public BuildingStatus getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(BuildingStatus buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public abstract ConsumablesPack generateResources();
    public abstract ConsumablesPack consumeResources();
    public abstract ConsumablesPack costOfBuildingInConsumables();
    public abstract UnitsPack costOfBuildingInConsumablesInUnits();

    public abstract int timeOfBuild();
    public String getName() {
        return name;
    }
    public abstract Integer getPriority();

    public int getCounter() {
        return counter;
    }
    public void decreaseCounter() {
        counter--;
    }
    public boolean isReady() {
        return (counter == 0);
    }

    @Override
    public int compareTo(Building o) {
        return o.getPriority().compareTo(this.getPriority());
    }

    public String getBuildingStatusAsString() {
        String status = "";

        switch (buildingStatus) {
            case CLOSED:
                status = "closed";
                break;
            case DAMAGED:
                status = "damaged";
                break;
            case WORKING:
                status = "working";
                break;
            case AWAITING:
                status = "awaiting";
                break;
            case IN_BUILD:
                status = "in build";
            default:
                break;
        }

        return status;
    }

    @Override
    public void damage() {
        Random random = new Random();
        int levelOfDamage = random.nextInt(101);

        if (levelOfDamage == 0) {
            return;
        }
        else if (levelOfDamage == 100 || buildingStatus == BuildingStatus.DAMAGED) {
            buildingStatus = BuildingStatus.DESTROYED;
        }

        buildingStatus = BuildingStatus.DAMAGED;
    }

    @Override
    public boolean fix() {
        if (buildingStatus != BuildingStatus.DAMAGED) {
            return false;
        }

        ConsumablesPack CP = costOfBuildingInConsumables();
        ConsumablesPack requiredCP = new ConsumablesPack(CP.getEnergy() / 2,
                                                     CP.getMarsMaterial()/ 2,
                                                     CP.getEarthMaterial() / 2,
                                                          CP.getWater() /2,
                                                          CP.getEarthMaterial() / 2,
                                                        CP.getOxygen() / 2);

        if (ResourcesManager.subtract(requiredCP, new UnitsPack(0, 0))) {
            buildingStatus = BuildingStatus.WORKING;
            return true;
        }

        return false;
    }
}
