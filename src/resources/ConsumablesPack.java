package resources;

public final class ConsumablesPack {
    private long energy;
    private long marsMaterial;
    private long earthMaterial;
    private long water;
    private long food;
    private long oxygen;

    public ConsumablesPack(long energy, long marsMaterial, long earthMaterial, long water, long food, long oxygen) {
        this.energy = energy;
        this.marsMaterial = marsMaterial;
        this.earthMaterial = earthMaterial;
        this.water = water;
        this.food = food;
        this.oxygen = oxygen;
    }

    public long getEnergy() { return energy; }
    public void setEnergy(long energy) { this.energy = energy; }

    public long getMarsMaterial() { return marsMaterial; }
    public void setMarsMaterial(long marsMaterial) { this.marsMaterial = marsMaterial; }

    public long getEarthMaterial() { return earthMaterial; }
    public void setEarthMaterial(long earthMaterial) { this.earthMaterial = earthMaterial; }

    public long getWater() { return water; }
    public void setWater(long water) { this.water = water; }

    public long getFood() { return food; }
    public void setFood(long food) { this.food = food; }

    public long getOxygen() { return oxygen; }
    public void setOxygen(long oxygen) { this.oxygen = oxygen; }

    public void addEnergy(long x){ this.energy += x;}
    public void addMarsMaterial(long x) { this.marsMaterial += x; }
    public void addEarthMaterial(long x) { this.earthMaterial += x; }
    public void addWater(long x) { this.water += x;}
    public void addFood(long x) { this.food += x;}
    public void addOxygen(long x) { this.oxygen += x;}

    public void subtractEnergy(long x){ this.energy -= x;}
    public void subtractMarsMaterial(long x) { this.marsMaterial -= x; }
    public void subtractEarthMaterial(long x) { this.earthMaterial -= x; }
    public void subtractWater(long x) { this.water -= x;}
    public void subtractFood(long x) { this.food -= x;}
    public void subtractOxygen(long x) { this.oxygen -= x;}

    public boolean isEnough(ConsumablesPack cP) {
        return this.getEnergy()>= cP.getEnergy()
                && this.getMarsMaterial() >= cP.getMarsMaterial()
                && this.getEarthMaterial() >= cP.getEarthMaterial()
                && this.getWater() >= cP.getWater()
                && this.getFood() >= cP.getFood()
                && this.getOxygen() >= cP.getOxygen();
    }

    public void zeroing() {
        this.energy = 0;
        this.marsMaterial = 0;
        this.earthMaterial = 0;
        this.water = 0;
        this.food = 0;
        this.oxygen = 0;
    }

    @Override
    public String toString() {
        return "Consumables Pack: " +
                "Energy {" + this.energy + "}" +
                "Water {" + this.water + "}" +
                "Oxygen {" + this.oxygen+ "}" +
                "Food {" + this.food + "}" +
                "Mars Material {" + this.marsMaterial + "}" +
                "Earth Material {" + this.earthMaterial+ "}";
    }
}
