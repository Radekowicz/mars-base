package events;

import buildings.BuildingManager;
import transport.TransportManager;

import java.util.ArrayList;
import java.util.List;

public class Sandstorm extends Event {
    public Sandstorm(EventListener eventListener) {
        super(eventListener);
    }

    @Override
    public String getDescribed() {
        return "DARUDE SANDSTORM";
    }

    @Override
    public void execute() {
        List<Destructible> destructibleObjects = new ArrayList<>();
        destructibleObjects.addAll(BuildingManager.getBuildings());

        super.execute();
    }
}
