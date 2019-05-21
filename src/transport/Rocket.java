package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public abstract class Rocket extends Transport {
    public Rocket(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity) {
        super(speed, name, maxCPCapacity, maxUPCapacity);
    }
}
