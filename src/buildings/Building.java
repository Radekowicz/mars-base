package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

public abstract class Building implements Comparable<Building> {
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
}
