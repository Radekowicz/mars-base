package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class SwiftRocket extends Rocket {
    private static int number = 1;

    public SwiftRocket() {
        super(1600, "Swift Rocket #" + number++,
                new ConsumablesPack(0,400, 400, 200, 0,0),
                new UnitsPack(5, 15));
    }

    @Override
    public ConsumablesPack costOfFix() {
        return new ConsumablesPack(250, 90, 180, 50, 30, 70);
    }

    @Override
    public ConsumablesPack requiredResourcesToStart() {
        return new ConsumablesPack(3000, 0,0,100,0,300);
    }

    @Override
    public int timeOfUnload() {
        return 4;
    }

    @Override
    public int timeOfLoad() {
        return 9;
    }
}
