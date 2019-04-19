package resources;

public class ResourcePack {

    private int energy;
    private int soil;
    private int food;

    public ResourcePack(int energy, int soil, int food) {
        this.energy = energy;
        this.soil = soil;
        this.food = food;
    }

    public void add(ResourcePack other) {
        energy += other.getEnergy();
        soil += other.getSoil();
        food += other.getFood();
    }

    public void subtract(ResourcePack other) {
        energy -= other.getEnergy();
        soil -= other.getSoil();
        food -= other.getFood();
    }

    public int getEnergy() {
        return energy;
    }

    public int getSoil() {
        return soil;
    }

    public int getFood() {
        return food;
    }

}

