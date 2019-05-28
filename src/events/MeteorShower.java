package events;

import buildings.BuildingManager;
import transport.TransportManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MeteorShower extends Event {
    public MeteorShower(EventListener eventListener) {
        super(eventListener);
    }

    /**
     * Runs the event process. Prepares description. In depends of the result of the event, send to EventListener describe with list of damaged objects.
     */
    @Override
    public void execute() {
        List<Destructible> destructibleObjects = new ArrayList<>();
        destructibleObjects.addAll(TransportManager.getTransports());
        destructibleObjects.addAll(BuildingManager.getBuildings());
        Random random = new Random();
        String listOfDamagedObject = "";
        int numberOfDamagedObject = 0;

        Collections.shuffle(destructibleObjects);

        for (Destructible destructibleObject: destructibleObjects) {
            if (random.nextInt(101) < 3) {
                destructibleObject.damage();
                numberOfDamagedObject++;
                listOfDamagedObject.concat(destructibleObject.getName()).concat(" ");
            }
        }

        String describe = "Meteros hit! " + numberOfDamagedObject + " objects have been damaged! ";

        if (numberOfDamagedObject > 0)  {
            describe.concat("List of damaged objects: ");
            describe.concat(listOfDamagedObject);
        }

        super.setDescribe(describe);
        super.execute();
    }
}
