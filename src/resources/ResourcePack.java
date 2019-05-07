package resources;

public class ResourcePack {

    private int energy;
    private int marsMaterial;
    private int earthMaterial;
    private int water;
    private int food;
    private int oxygen;

    public ResourcePack(int energy, int marsMaterial, int earthMaterial, int water, int food, int oxygen) {
        this.energy = energy;
        this.marsMaterial = marsMaterial;
        this.earthMaterial = earthMaterial;
        this.water = water;
        this.food = food;
        this.oxygen = oxygen;
    }

    public void add(ResourcePack other) {
        energy += other.getEnergy();
        marsMaterial += other.getMarsMaterial();
        earthMaterial += other.getEarthMaterial()
        food += other.getFood();
        water += other.getWater();
        oxygen += other.getOxygen();
    }

    public void subtract(ResourcePack other) {
        energy -= other.getEnergy();
        marsMaterial -= other.getMarsMaterial();
        earthMaterial -= other.getEarthMaterial()
        food -= other.getFood();
        water -= other.getWater();
        oxygen -= other.getOxygen();
    }

    public int getEnergy() {
        return energy;
    }

    public int getFood() {
        return food;
    }

    public int getMarsMaterial() {
        return marsMaterial;
    }

    public int getEarthMaterial() {
        return earthMaterial;
    }

    public int getWater() {
        return water;
    }

    public int getOxygen() {
        return oxygen;
    }
}

