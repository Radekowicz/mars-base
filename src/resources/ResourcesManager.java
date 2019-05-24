package resources;

import buildings.BuildingManager;

public final class ResourcesManager {
    private static ResourcesManager resourcesManager = null;

    private static ConsumablesPack consumablesPack;
    private static UnitsPack unitsPack;

    private ResourcesManager(ConsumablesPack consumablesPack, UnitsPack unitsPack) {
        this.consumablesPack = consumablesPack;
        this.unitsPack = unitsPack;
    }

    public static void initializeResourcesManager(ConsumablesPack consumablesPack, UnitsPack unitsPack){
        if(resourcesManager == null)
            resourcesManager = new ResourcesManager(consumablesPack, unitsPack);
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

    //jeśli gracz będzie chciał dodać więcej Human niż jest Capacity to zostanie odrzucony a interfejs wypisze ile Max Human może dodać ( howManyPlacesAvailable() )

    public static boolean canAdd (UnitsPack uP) {
        return(BuildingManager.getMaxHumanCapacity() - getHumanStatus() >= uP.getHumans());
    }

    public static long howManyPlacesAvailable() {
        return BuildingManager.getMaxHumanCapacity() - getHumanStatus();
    }

    public static void add(UnitsPack uP) {
        if(canAdd(uP)) {
            unitsPack.addHumans(uP.getHumans());
        }
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

    public static void showRecources() {
        System.out.println("ENERGY: " + consumablesPack.getEnergy());
        System.out.println("MARSMATERIAL: " + consumablesPack.getMarsMaterial());
        System.out.println("EARTHMATERIAL: " + consumablesPack.getEarthMaterial());
        System.out.println("WATER: " + consumablesPack.getWater());
        System.out.println("FOOD: " + consumablesPack.getFood());
        System.out.println("OXYGEN: " + consumablesPack.getOxygen());
        System.out.println("HUMANS: " + unitsPack.getHumans());
        System.out.println("ROBOTS: " + unitsPack.getRobots());
    }
    public static void zeroing(){
        consumablesPack.zeroing();
        unitsPack.zeroing();
    }
}
