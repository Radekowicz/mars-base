package buildings;

import resources.ResourcePack;

public class BuildingManager {
    ResourcePack resourcePack;

    public boolean canBuild(Building building) {
        if (resourcePack.getEnergy() >= building.costOfBuilding().getEnergy() && resourcePack.getSoil() >= building.costOfBuilding().getSoil()) return true;
        else return false;
    }
}