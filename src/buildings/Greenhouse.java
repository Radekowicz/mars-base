package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

/**
 * Building which produces food
 */
public class Greenhouse extends Building {
    private static int buildingNumber = 1;

    public Greenhouse() {
        super("Greenhouse #" + buildingNumber++);
    }


    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(0, 0,0,0, 100, 0);
    }

    @Override
    public ConsumablesPack consumeResources() {
        return new ConsumablesPack(20, 0,0,50, 0, 0);
    }

    @Override
    public ConsumablesPack costOfBuildingInConsumables() { return new ConsumablesPack(200, 500,300,0, 0, 0); }

    @Override
    public UnitsPack costOfBuildingInUnits() {
        return new UnitsPack(0, 10);
    }

    @Override
    public int timeOfBuild() {
        return 4;
    }

    @Override
    public Integer getPriority() {
        return 1;
    }
}
