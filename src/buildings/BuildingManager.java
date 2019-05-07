package buildings;

import resources.ResourcePack;
import buildings.Building;
import java.util.ArrayList;
import java.util.List;

public class BuildingManager {
    ResourcePack resourcePack;
    private List<Building> buildings;
    private List<Building> awaitingBuildings;

    public BuildingManager(ResourcePack resourcePack, List<Building> buildings) {
        this.resourcePack = resourcePack;
        this.buildings = buildings;
    }

    public boolean canBuild(Building building) {
        if (resourcePack.getEnergy() >= building.costOfBuilding().getEnergy() && resourcePack.getSoil() >= building.costOfBuilding().getSoil()) return true;
        else return false;
    }

    public void build(Building building) {

    }
}