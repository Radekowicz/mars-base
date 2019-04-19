import buildings.Building;
import resources.ResourcePack;

import java.util.List;

public class Simulator {
    private int time;
    private ResourcePack resources;
    private List<Building> buildings;

    public Simulator(ResourcePack resources, List<Building> buildings) {
        this.time = 0;
        this.resources = resources;
        this.buildings = buildings;
    }

    public void tick() {
        time++;
        for (Building building : buildings) {
            resources.add(building.generateResources());
            resources.subtract(building.consumeResources());
        }
    }

    public void print() {


        System.out.println("Current time: " + time);

        System.out.println("Resources: ");
        System.out.println(" Energy: " + resources.getEnergy());
        System.out.println(" Soil: " + resources.getSoil());
        System.out.println(" Food: " + resources.getFood());

        System.out.println("Buildings: ");
        for (Building building : buildings) {
            System.out.println(building.getName());
        }
    }
}

