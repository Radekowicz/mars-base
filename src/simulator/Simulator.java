package simulator;

import buildings.BuildingManager;
import resources.ResourcesManager;
import transport.TransportManager;

public class Simulator {
    private BuildingManager buildingManager = new BuildingManager();
    private ResourcesManager resourcesManager = new ResourcesManager();
    private TransportManager transportManager = new TransportManager();

    public void run() {
        while (true) {
            //buildingManager.update();
            //transportManager.update();
            System.out.println("MAIN LOOP");
        }
    }
}
