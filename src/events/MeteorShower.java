package events;

import buildings.BuildingManager;
import transport.TransportManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeteorShower extends Event {
    public MeteorShower(EventListener eventListener) {
        super(eventListener);
    }

    @Override
    public String getDescribed() {
        return "METEOR HIT";
    }

    @Override
    public void execute() {
        List<Destructible> destructibleObjects = new ArrayList<>();
        destructibleObjects.addAll(TransportManager.getTransports());
        destructibleObjects.addAll(BuildingManager.getBuildings());
        Random random = new Random();

        for (Destructible destructibleObject: destructibleObjects) {
            if (random.nextInt(101) < 5)
                destructibleObject.damage();
        }

        super.execute();
    }
}
