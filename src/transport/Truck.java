package transport;

import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.Arrays;

public class Truck extends Transport {
    private static int number = 1;

    public Truck() {
        super(100, "Truck #" + number++,
                new ConsumablesPack(0,50,50,200,0,0),
                new UnitsPack(2,0),
                new ArrayList<>(Arrays.asList(Place.MARS)));
    }

    @Override
    public ConsumablesPack costOfFix() {
        return new ConsumablesPack(20,20,40,10,5,5);
    }

    @Override
    public ConsumablesPack requiredResourcesToStart() {
        return new ConsumablesPack(50,0,0,5,0,0);
    }

    @Override
    public boolean send(Place target, int distanceToDestiny) {
        if (super.getTarget() != null || !ResourcesManager.isEnough(requiredResourcesToStart()))
            return false;

        return super.send(target, distanceToDestiny);
    }
}
