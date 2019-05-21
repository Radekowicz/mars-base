package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public class Truck extends Transport {
    private static int number = 1;

    public Truck() {
        super(10, "Truck #" + number++,
                new ConsumablesPack(0,50,50,200,0,0),
                new UnitsPack(2,0));
    }

    @Override
    public ConsumablesPack costOfFix() {
        return new ConsumablesPack(20,20,40,10,5,5);
    }
}
