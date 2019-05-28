package resources;

public final class ConsumablesPack {
    private long energy;
    private long marsMaterial;
    private long earthMaterial;
    private long water;
    private long food;
    private long oxygen;

    /**
     * Constuct
     * @param energy
     * @param marsMaterial
     * @param earthMaterial
     * @param water
     * @param food
     * @param oxygen
     */
    public ConsumablesPack(long energy, long marsMaterial, long earthMaterial, long water, long food, long oxygen) {
        this.energy = energy;
        this.marsMaterial = marsMaterial;
        this.earthMaterial = earthMaterial;
        this.water = water;
        this.food = food;
        this.oxygen = oxygen;
    }

    public long getEnergy() {
        return energy;
    }

    public long getMarsMaterial() {
        return marsMaterial;
    }

    public long getEarthMaterial() {
        return earthMaterial;
    }

    public long getWater() {
        return water;
    }

    public long getFood() {
        return food;
    }

    public long getOxygen() {
        return oxygen;
    }

    public void addEnergy(long x){ this.energy += x;}
    public void addMarsMaterial(long x) { this.marsMaterial += x; }
    public void addEarthMaterial(long x) { this.earthMaterial += x; }
    public void addWater(long x) { this.water += x;}
    public void addFood(long x) { this.food += x;}
    public void addOxygen(long x) { this.oxygen += x;}

    public void add( ConsumablesPack otherCp){
        this.energy += otherCp.energy;
        this.marsMaterial += otherCp.marsMaterial;
        this.earthMaterial += otherCp.earthMaterial;
        this.water += otherCp.water;
        this.food +=otherCp.food;
        this.oxygen += otherCp.oxygen;
    }

    public void subtractEnergy(long x){ this.energy -= x; }
    public void subtractMarsMaterial(long x) {
        this.marsMaterial -= x;
    }
    public void subtractEarthMaterial(long x) {
        this.earthMaterial -= x;
    }
    public void subtractWater(long x) {
        this.water -= x;
    }
    public void subtractFood(long x) {
        this.food -= x;
    }
    public void subtractOxygen(long x) {
        this.oxygen -= x;
    }

    public boolean subtract(ConsumablesPack otherCp){
        if(isEnough(otherCp)) {
            this.energy -= otherCp.energy;
            this.marsMaterial -= otherCp.marsMaterial;
            this.earthMaterial -= otherCp.earthMaterial;
            this.food -= otherCp.food;
            this.water -= otherCp.water;
            this.oxygen -= otherCp.oxygen;
            return true;
        }
        return false;
    }



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
