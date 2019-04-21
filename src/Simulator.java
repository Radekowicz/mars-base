import buildings.Building;
import resources.ResourcePack;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Simulator {
    private int time;
    private ResourcePack resources;
    private List<Building> buildings;
    private List<Building> awaitingBuildings;

    public Simulator(ResourcePack resources, List<Building> buildings) {
        this.time = 0;
        this.resources = resources;
        this.buildings = buildings;
        this.awaitingBuildings = new ArrayList<>();
    }

    public void tick() {
        time++;

        //resource management
        for (Building building : buildings) {
            resources.add(building.generateResources());
            resources.subtract(building.consumeResources());
        }

        //awaiting building management
        List<Building> toRemove = new ArrayList<>();
        for (Building building : awaitingBuildings) {
            building.decreaseCounter();
            if (building.isReady()) {
                buildings.add(building);
                toRemove.add(building);
            }
        }
        awaitingBuildings.removeAll(toRemove);
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

        for(Building building : awaitingBuildings) {
                System.out.println(building.getName() + " building time left: " + building.getCounter());
        }


    }

    public void build(Building building) {
        awaitingBuildings.add(building);
    }
}

