package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class Hub extends Building {
    private static int buildingNumber = 1;

    public Hub() {
        super("Hub #" + buildingNumber++);
    }

    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(0,0,0,0,0,0);
    }

    @Override
    public ConsumablesPack consumeResources() {
        return new ConsumablesPack(100,0,0,100,100,200);
    }

    @Override
    public ConsumablesPack costOfBuildingInConsumables() {
        return new ConsumablesPack(1000, 2000, 1000, 0, 0, 0);
    }

    @Override
    public UnitsPack costOfBuildingInConsumablesInUnits() {
        return new UnitsPack(0, 10);
    }

    @Override
    public int timeOfBuild() {
        return 5;
    }

    @Override
    public Integer getPriority() {
        return 5;
    }
}
