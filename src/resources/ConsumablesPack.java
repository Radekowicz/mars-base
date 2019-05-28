package resources;

public final class ConsumablesPack {
    private long energy;
    private long marsMaterial;
    private long earthMaterial;
    private long water;
    private long food;
    private long oxygen;

    /**
     * Sets resources
     * @param energy represents energy
     * @param marsMaterial represents mars material
     * @param earthMaterial represents earth material
     * @param water represents water
     * @param food represents food
     * @param oxygen represents oxygen
     */
    public ConsumablesPack(long energy, long marsMaterial, long earthMaterial, long water, long food, long oxygen) {
        this.energy = energy;
        this.marsMaterial = marsMaterial;
        this.earthMaterial = earthMaterial;
        this.water = water;
        this.food = food;
        this.oxygen = oxygen;
    }

    /**
     * @return returns amount level of energy
     */
    public long getEnergy() {
        return energy;
    }

    /**
     * @return returns amount of mars material
     */
    public long getMarsMaterial() {
        return marsMaterial;
    }

    /**
     * @return returns amount of earth material
     */
    public long getEarthMaterial() {
        return earthMaterial;
    }

    /**
     * @return returns amount of water
     */
    public long getWater() {
        return water;
    }

    /**
     * @return returns amount of food
     */
    public long getFood() {
        return food;
    }

    /**
     * @return returns amount of oxygen
     */
    public long getOxygen() {
        return oxygen;
    }

    /**
     * adds particular amount of energy
     * @param x amount of energy which will be added
     */
    public void addEnergy(long x){ this.energy += x;}

    /**
     * adds particular amount of mars material
     * @param x amount of mars material which will be added
     */
    public void addMarsMaterial(long x) { this.marsMaterial += x; }

    /**
     * adds particular amount of earth material
     * @param x amount of earth material which will be added
     */
    public void addEarthMaterial(long x) { this.earthMaterial += x; }

    /**
     * adds particular amount of water material
     * @param x amount of water which will be added
     */
    public void addWater(long x) { this.water += x;}

    /**
     * adds particular amount of food
     * @param x amount of food which will be added
     */
    public void addFood(long x) { this.food += x;}

    /**
     * adds particular amount of oxygen
     * @param x amount of oxygen which will be added
     */
    public void addOxygen(long x) { this.oxygen += x;}

    /**
     * adds particular amount of every resource contained in ConsumablesPack typed object to the current container
     * @param otherCp ConsumablesPack variable which will be added
     */
    public void add( ConsumablesPack otherCp){
        this.energy += otherCp.energy;
        this.marsMaterial += otherCp.marsMaterial;
        this.earthMaterial += otherCp.earthMaterial;
        this.water += otherCp.water;
        this.food +=otherCp.food;
        this.oxygen += otherCp.oxygen;
    }

    /**
     * substract particular amount of energy
     * @param x amount of energy which will be subtracted
     */
    public void subtractEnergy(long x){ this.energy -= x; }

    /**
     * subtract particular amount of mars material
     * @param x amount of mars material which will be subtracted
     */
    public void subtractMarsMaterial(long x) { this.marsMaterial -= x; }

    /**
     * subtracts particular amount of earth material
     * @param x amount of earth material which will be subtracted
     */
    public void subtractEarthMaterial(long x) { this.earthMaterial -= x; }

    /**
     * subtracts particular amount of water
     * @param x amount of water which will be subtracts
     */
    public void subtractWater(long x) { this.water -= x; }

    /**
     * subtracts particular amount of food
     * @param x amount of food which wil be subtracted
     */
    public void subtractFood(long x) { this.food -= x; }

    /**
     * subtracts particular amount of oxygen
     * @param x amount of oxygen which will be subtracted
     */
    public void subtractOxygen(long x) { this.oxygen -= x; }

    /**
     * subtracts praticular of each resource
     * @param otherCp ConsumablesPack variable which will be subtracted
     * @return returns true or false, in case if there is lower amount of resources then amount of them that we want to subtract
     */
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


    /**
     * checks if there is enough resources that after subtraction amount of every resource will be grater then zero
     * @param cP ConsumablesPack variable which will be compared
     * @return true or false, true -> We have more resources than param, false -> at least one of resources is smaller then param
     */
    public boolean isEnough(ConsumablesPack cP) {
        return this.getEnergy()>= cP.getEnergy()
                && this.getMarsMaterial() >= cP.getMarsMaterial()
                && this.getEarthMaterial() >= cP.getEarthMaterial()
                && this.getWater() >= cP.getWater()
                && this.getFood() >= cP.getFood()
                && this.getOxygen() >= cP.getOxygen();
    }

    /**
     * sets all resources to zero
     */
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
