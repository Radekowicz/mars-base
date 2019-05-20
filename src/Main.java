import engine.MarsBaseSimulator;
import gui.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        MarsBaseSimulator marsBaseSimulator = new MarsBaseSimulator();

        EventQueue.invokeLater(() -> {
            new MainFrame();
        });

//        List<Building> buildings = BuildingManager.getBuildings();
//
//        for (Building building: buildings) {
//            System.out.println(building.getBuildingStatusAsString());
//        }

        marsBaseSimulator.run();
    }
}
