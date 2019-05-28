package events;

import buildings.BuildingManager;
import buildings.SolarPanel;
import transport.TransportManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sandstorm extends Event {
    public Sandstorm(EventListener eventListener) {
        super(eventListener);
    }

    /**
     * Runs the event process. Prepares description. In depends of the result of the event, send to EventListener describe with list of damaged objects.
     */
    @Override
    public void execute() {
        Random random = new Random();
        List<Destructible> destructibleObjects = new ArrayList<>();
        destructibleObjects.addAll(BuildingManager.getBuildings());
        String listOfDamagedObject = "";
        int numberOfDamagedObject = 0;

        Collections.shuffle(destructibleObjects);

        for (Destructible destructibleObject: destructibleObjects) {
            if (destructibleObject instanceof SolarPanel)
                if (random.nextInt(101) < 3) {
                    destructibleObject.damage();
                    numberOfDamagedObject++;
                    listOfDamagedObject.concat(destructibleObject.getName() + " ");
                }
        }

        String describe = "Darude Sandstorm!" + numberOfDamagedObject + " solar panels have been damaged! ";

        if (numberOfDamagedObject > 0)  {
            describe.concat("List of damaged solar panels: ");
            describe.concat(listOfDamagedObject);
        }

        super.setDescribe(describe);
        super.execute();
    }
}
