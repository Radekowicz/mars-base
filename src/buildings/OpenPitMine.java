package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

/**
 * Building which produces mars material
 */
public class OpenPitMine extends Building {
    private static int buildingNumber = 1;

    public OpenPitMine() {
        super("OpenPitMine #" + buildingNumber++);
    }

    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(0,100,0,0,0,0);
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
    public UnitsPack costOfBuildingInUnits() {
        return new UnitsPack(0, 8);
    }

    @Override
    public int timeOfBuild() {
        return 3;
    }

    @Override
    public Integer getPriority() {
        return 2;
    }
}
