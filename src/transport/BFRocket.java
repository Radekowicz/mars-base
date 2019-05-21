package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class BFRocket extends Rocket {
    private static int number = 1;

    public BFRocket(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity) {
        super(900, "BFRocket #" + number++,
                new ConsumablesPack(0,400, 400, 200, 0,0),
                new UnitsPack(10, 40));
    }

    @Override
    public ConsumablesPack costOfFix() {
        return new ConsumablesPack(360, 140, 240, 70, 50, 100);
    }
}
