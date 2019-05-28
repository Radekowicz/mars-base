package buildings;

import events.Destructible;
import events.Fixable;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.Random;

/**
 * An a abstract class
 */
public abstract class Building implements Comparable<Building>, Destructible, Fixable {
    private int counter = timeOfBuild();
    private BuildingStatus buildingStatus;
    private String name;
    private boolean checked;

    /**
     * Sets building name and buildg status as working
     * @param name represents building's name
     */
    public Building(String name) {
        this.name = name;
        buildingStatus = BuildingStatus.WORKING;
        checked = false;
    }

    /**
     * Returns building's status
     * @return BuildingStatus
     */
    public BuildingStatus getBuildingStatus() {
        return buildingStatus;
    }

    /**
     * Sets building's status
     * @param buildingStatus represents building status
     */
    public void setBuildingStatus(BuildingStatus buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    /**
     * Sets how many resources does the building generates
     * @return ConsumablesPack
     */
    public abstract ConsumablesPack generateResources();

    /**
     * Sets how many resources does the building consumes
     * @return ConsumablesPack
     */
    public abstract ConsumablesPack consumeResources();

    /**
     * Sets how many resources does it cost to build a building
     * @return ConsumablesPack
     */
    public abstract ConsumablesPack costOfBuildingInConsumables();

    /**
     * Sets how many units does take to build a building
     * @return UnitsPack
     */
    public abstract UnitsPack costOfBuildingInUnits();

    /**
     * Sets time needed to build a building
     * @return timeOfBuild
     */
    public abstract int timeOfBuild();

    /**
     * Returns building's name as String
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Declares priority of the building, when there is no resources to power the buildings,
     * they are closed in order from lowest to highest integer
     * @return priority
     */
    public abstract Integer getPriority();

    /**
     * Decreases counter that is used in counting time to build a building
     */
    public void decreaseCounter() {
        counter--;
    }

    /**
     * Declares if the build time is over
     * @return TRUE if the counter = 0, otherwise return FALSE
     */
    public boolean isReady() {
        return (counter == 0);
    }

    /**
     * Compares buildings' priorities (integers)
     * @param o represents building that we compare other building to
     * @return -1 if o greater than this, 0 if they are equal, 1 if 0 less than this
     */
    @Override
    public int compareTo(Building o) {
        return o.getPriority().compareTo(this.getPriority());
    }

    /**
     * Returns {@link BuildingStatus} as String
     * @return status
     */
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
                status = "in canBuild";
            default:
                break;
        }

        return status;
    }

    /**
     * Declares how damaged will the building be after an event etc. MeteorShower
     */
    @Override
    public void damage() {
        Random random = new Random();
        int levelOfDamage = random.nextInt(101);

        if (levelOfDamage == 0) {
            return;
        }
        else if (levelOfDamage == 100 || buildingStatus == BuildingStatus.DAMAGED || buildingStatus == BuildingStatus.IN_BUILD) {
            buildingStatus = BuildingStatus.DESTROYED;
        }

        buildingStatus = BuildingStatus.DAMAGED;
    }

    /**
     * Allows the user to fix damaged building for half of it's building cost
     * @return
     */
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}