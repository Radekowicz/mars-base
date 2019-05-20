package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class PrintStation extends Building{
    private static int buildingNumber = 1;

    public PrintStation() {
        super("PrintStation #" + buildingNumber++);
    }

    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(0,0,0,0,0,0);
    }

    @Override
    public ConsumablesPack consumeResources() {
        return new ConsumablesPack(0,0,0,0,0,0);
    }

    @Override
    public ConsumablesPack costOfBuildingInConsumables() {
        return new ConsumablesPack(1000, 2000, 1000, 0, 0, 0);
    }

    @Override
    public UnitsPack costOfBuildingInConsumablesInUnits() {
        return new UnitsPack(0, 15);
    }

    @Override
    public int timeOfBuild() {
        return 8;
    }

    @Override
    public Integer getPriority() {
        return 2;
    }

}
