package resources;

public final class ResourcesManager {
    private static ResourcesManager resourcesManager = null;

    private static ConsumablesPack consumablesPack;
    private static UnitsPack unitsPack;

    private ResourcesManager(ConsumablesPack consumablesPack, UnitsPack unitsPack) {
        this.consumablesPack = consumablesPack;
        this.unitsPack = unitsPack;
    }

    public static ResourcesManager initializeResourcesManager(ConsumablesPack consumablesPack, UnitsPack unitsPack){
        if(resourcesManager == null)
            resourcesManager = new ResourcesManager(consumablesPack, unitsPack);
        return resourcesManager;
    }

    public static void add(ConsumablesPack cP, UnitsPack uP){
        add(cP);
        add(uP);
    }

    public static void add(ConsumablesPack cP) {
        consumablesPack.addEnergy(cP.getEnergy());
        consumablesPack.addEarthMaterial(cP.getEarthMaterial());
        consumablesPack.addMarsMaterial(cP.getMarsMaterial());
        consumablesPack.addFood(cP.getFood());
        consumablesPack.addWater(cP.getWater());
        consumablesPack.addOxygen(cP.getOxygen());
    }

    public static void add(UnitsPack uP) {
        unitsPack.addHumans(uP.getHumans());
        unitsPack.addRobots(uP.getRobots());
    }

    public static boolean subtract(ConsumablesPack cP, UnitsPack uP){
        if(resourcesManager.isEnough(cP,uP)) {
            consumablesPack.subtractEnergy(cP.getEnergy());
            consumablesPack.subtractEarthMaterial(cP.getEarthMaterial());
            consumablesPack.subtractMarsMaterial(cP.getMarsMaterial());
            consumablesPack.subtractFood(cP.getFood());
            consumablesPack.subtractWater(cP.getWater());
            consumablesPack.subtractOxygen(cP.getOxygen());
            unitsPack.subtractHumans(uP.getHumans());
            unitsPack.subtractRobots(uP.getRobots());
            return true;
        }
        return false;
    }

    public static boolean subtract(ConsumablesPack cP) {
        if (isEnough(cP)) {
            consumablesPack.subtractEnergy(cP.getEnergy());
            consumablesPack.subtractEarthMaterial(cP.getEarthMaterial());
            consumablesPack.subtractMarsMaterial(cP.getMarsMaterial());
            consumablesPack.subtractFood(cP.getFood());
            consumablesPack.subtractWater(cP.getWater());
            consumablesPack.subtractOxygen(cP.getOxygen());
            return true;
        }
        return false;
    }

    public static boolean subtract(UnitsPack uP) {
        if (isEnough(uP)) {
            unitsPack.subtractHumans(uP.getHumans());
            unitsPack.subtractRobots(uP.getRobots());
            return true;
        }
        return false;
    }

    public static boolean isEnough(ConsumablesPack cP, UnitsPack uP){
        return consumablesPack.isEnough(cP) && unitsPack.isEnough(uP);
    }

    public static boolean isEnough(ConsumablesPack cP){
        return consumablesPack.isEnough(cP);
    }

    public static boolean isEnough(UnitsPack uP){
        return unitsPack.isEnough(uP);
    }

    public static long getEnergyStatus() {
        return consumablesPack.getEnergy();
    }

    public static long getWaterStatus() {
        return consumablesPack.getWater();
    }

    public static long getOxygenStatus() {
        return consumablesPack.getOxygen();
    }

    public static long getFoodStatus() {
        return consumablesPack.getFood();
    }

    public static long getMarsMaterialStatus() {
        return consumablesPack.getMarsMaterial();
    }

    public static long getEarthMaterialStatus() {
        return consumablesPack.getEarthMaterial();
    }

    public static long getHumanStatus() {
        return unitsPack.getHumans();
    }

    public static long getRobotStatus() {
        return unitsPack.getRobots();
    }

}
