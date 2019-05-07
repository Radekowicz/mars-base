package buildings;

import resources.ResourcePack;

public abstract class Building {
    private int counter = timeOfBuild();
    private BuildingStatus buildingStatus;


    public abstract ResourcePack generateResources();
    public abstract ResourcePack consumeResources();
    public abstract ResourcePack costOfBuilding();

    public abstract int timeOfBuild();
    public abstract String getName();

    public void decreaseCounter() {
        counter--;
    }

    public boolean isReady() {
        return (counter == 0);
    }

    public int getCounter() {
        return counter;
    }

}
