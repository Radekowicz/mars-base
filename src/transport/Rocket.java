package transport;

import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Rocket extends Transport {
    public Rocket(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity) {
        super(speed, name, maxCPCapacity, maxUPCapacity, new ArrayList<>(Arrays.asList(
                Place.ASTEROID, Place.ASTEROID, Place.EARTH, Place.MARS)));
    }

    @Override
    public boolean send(Place target, int distanceToDestiny) {
        if (super.getTarget() != null || !ResourcesManager.isEnough(requiredResourcesToStart()) || super.getCurrentPlace() == target)
            return false;

        return super.send(target, distanceToDestiny);
    }
}
