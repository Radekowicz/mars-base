package buildings;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class ColdFusionPowerPlant extends Building {
    private static int buildingNumber = 1;

    public ColdFusionPowerPlant() {
        super("Cold Fusion Power Plant #" + buildingNumber++);
    }

    @Override
    public ConsumablesPack generateResources() {
        return new ConsumablesPack(1000, 0,0,0,0,0);
    }

    @Override
    public ConsumablesPack consumeResources() {
        return new ConsumablesPack(0,0,0,10,0,5);
    }

    @Override
    public ConsumablesPack costOfBuildingInConsumables() {
        return new ConsumablesPack(3000, 1000, 2000, 200, 0, 30);
    }

    @Override
    public UnitsPack costOfBuildingInUnits() {
        return new UnitsPack(5, 20);
    }

    @Override
    public int timeOfBuild() {
        return 20;
    }

    @Override
    public Integer getPriority() {
        return 2;
    }
}
