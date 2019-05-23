package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class BFRocket extends Rocket {
    private static int number = 1;

    public BFRocket() {
        super(900, "BFRocket #" + number++,
                new ConsumablesPack(0,400, 400, 200, 0,0),
                new UnitsPack(10, 40));
    }

    @Override
    public ConsumablesPack costOfFix() {
        return new ConsumablesPack(360, 140, 240, 70, 50, 100);
    }

    @Override
    public ConsumablesPack requiredResourcesToStart() {
        return new ConsumablesPack(14000, 0,0,0,0,800);
    }

    @Override
    public int timeOfUnload() {
        return 7;
    }

    @Override
    public int timeOfLoad() {
        return 12;
    }
}
