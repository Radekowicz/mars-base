package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class OxygenGenerator extends Building {
    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(0,0,0,0,0,200);
    }

    @Override
    public ConsumablesPack consumeResources() {
        return new ConsumablesPack(80,0,0,0,0,0);
    }

    @Override
    public ConsumablesPack costOfBuildingInConsumables() {
        return new ConsumablesPack(1000, 2000, 1000, 0, 0, 0);
    }

    @Override
    public UnitsPack costOfBuildingInConsumablesInUnits() {
        return new UnitsPack(0, 8);
    }

    @Override
    public int timeOfBuild() {
        return 3;
    }

    @Override
    public String getName() {
        return "Oxygen Generator";
    }

    @Override
    public Integer getPriority() {
        return 4;
    }
}
