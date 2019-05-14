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
        consumablesPack.addEnergy(cP.getEnergy());
        consumablesPack.addEarthMaterial(cP.getEarthMaterial());
        consumablesPack.addMarsMaterial(cP.getMarsMaterial());
        consumablesPack.addFood(cP.getFood());
        consumablesPack.addWater(cP.getWater());
        consumablesPack.addOxygen(cP.getOxygen());
        unitsPack.addHumans(uP.getHumans());
        unitsPack.addRobots(uP.getRobots());
    }
    public static void subtract(ConsumablesPack cP, UnitsPack uP){
        if(resourcesManager.isEnough(cP,uP))
        {
            consumablesPack.subtractEnergy(cP.getEnergy());
            consumablesPack.subtractEarthMaterial(cP.getEarthMaterial());
            consumablesPack.subtractMarsMaterial(cP.getMarsMaterial());
            consumablesPack.subtractFood(cP.getFood());
            consumablesPack.subtractWater(cP.getWater());
            consumablesPack.subtractOxygen(cP.getOxygen());
            unitsPack.subtractHumans(uP.getHumans());
            unitsPack.subtractRobots(uP.getRobots());
        }
    }
    public static void showRecources(){
        System.out.println("ENERGY: "+consumablesPack.getEnergy());
        System.out.println("MARSMATERIAL: "+consumablesPack.getMarsMaterial());
        System.out.println("EARTHMATERIAL: "+consumablesPack.getEarthMaterial());
        System.out.println("WATER: "+consumablesPack.getWater());
        System.out.println("FOOD: "+consumablesPack.getFood());
        System.out.println("OXYGEN: "+consumablesPack.getOxygen());
        System.out.println("HUMANS: "+unitsPack.getHumans());
        System.out.println("ROBOTS: "+unitsPack.getRobots());
    }

    public static boolean isEnough(ConsumablesPack cP, UnitsPack uP){
        return consumablesPack.isEnough(cP) && unitsPack.isEnough(uP);
    }
}
