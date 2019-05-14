package buildings;

import resources.ConsumablesPack;
import resources.ConsumablesPack;
import resources.UnitsPack;

public abstract class Building implements Comparable<Building> {

    private int counter = timeOfBuild();
    private BuildingStatus buildingStatus;

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
    public abstract String getName();
    public abstract Integer getPriority();

    public void decreaseCounter() {
        counter--;
    }

    public boolean isReady() {
        return (counter == 0);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public int compareTo(Building o) {
        return o.getPriority().compareTo(this.getPriority());
    }
}
