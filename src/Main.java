import buildings.Building;
import buildings.Greenhouse;
import buildings.Hub;
import buildings.PowerStation;
import resources.ResourcePack;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {

        ResourcePack resourcePack = new ResourcePack(100, 100, 100);
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Hub());
        buildings.add(new PowerStation());
        buildings.add(new Greenhouse());

        Simulator simulator = new Simulator(resourcePack, buildings);

        int counter = 0;
        while(true) {
            simulator.print();
            simulator.tick();
            Thread.sleep(1000);
            counter++;
            if(counter == 0) simulator.build(new PowerStation());
            if(counter == 3) simulator.build(new Hub());
            if(counter == 7) simulator.build(new Greenhouse());
        }
    }
}
