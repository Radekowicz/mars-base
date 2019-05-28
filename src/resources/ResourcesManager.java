package resources;

import buildings.BuildingManager;

public final class ResourcesManager {
    private static ResourcesManager resourcesManager = null;

    private static ConsumablesPack consumablesPack;
    private static UnitsPack unitsPack;

    /**
     * private constructor
     * @param cP represents consumables materials
     * @param uP represents units
     */
    private ResourcesManager(ConsumablesPack cP, UnitsPack uP) {
        consumablesPack = cP;
        unitsPack = uP;
    }

    /**
     * method which is being used to create only one instance of ResourcesManager
     * @param cP initial values of consumables resources
     * @param uP intiial value of units
     */
    public static void initializeResourcesManager(ConsumablesPack cP, UnitsPack uP){
        if(resourcesManager == null)
            resourcesManager = new ResourcesManager(cP, uP);
    }

    /**
     * checks if caller is resourceManagerTest, if it is method is reseting resources
     * @param cP
     * @param uP
     */
    public static void reset(ConsumablesPack cP, UnitsPack uP){
        String caller = new Exception().getStackTrace()[1].getClassName();
        if (!caller.equals("resources.ResourcesManagerTest"))
           return;

        resourcesManager = new ResourcesManager(cP, uP);
    }

    /**
     * adds consumable resources and units
     * @param cP consumable resources which will be added
     * @param uP consumable resources which will be added
     */
    public static void add(ConsumablesPack cP, UnitsPack uP){
        add(uP);
        add(cP);
    }

    /**
     * adds consumables resources
     * @param cP consumable resources which will be added
     */
    public static void add(ConsumablesPack cP) {
        consumablesPack.add(cP);
    }

    //jeśli gracz będzie chciał dodać więcej Human niż jest Capacity to zostanie odrzucony a interfejs wypisze ile Max Human może dodać ( howManyPlacesAvailable() )

    /**
     * checks if there is enough space for people to come to mars
     * @param uP
     * @return
     */
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

    /**
     * subtracts consumables and units
     * @param cP
     * @param uP
     * @return returs if subtractions took place or not
     */
    public static boolean subtract(ConsumablesPack cP, UnitsPack uP){
        if(resourcesManager.isEnough(cP,uP)) {
            consumablesPack.subtract(cP);
            unitsPack.subtract(uP);
            return true;
        }
        return false;
    }

    public static void update() {
        long maxHumansCapacity = BuildingManager.getMaxHumanCapacity();

        if (maxHumansCapacity < unitsPack.getHumans()) {
            unitsPack.subtractHumans(unitsPack.getHumans() - maxHumansCapacity);
        }
    }

    public static boolean subtract(ConsumablesPack cP) {
        if (isEnough(cP)) {
            consumablesPack.subtract(cP);
            return true;
        }
        return false;
    }

    /**
     * subtracts units
     * @param uP
     * @return returs if subtractions took place or not
     */
    public static boolean subtract(UnitsPack uP) {
        if (isEnough(uP)) {
            unitsPack.subtractHumans(uP.getHumans());
            unitsPack.subtractRobots(uP.getRobots());
            return true;
        }
        return false;
    }

    /**
     * checks if there is enough consumables and units
     * @param cP
     * @param uP
     * @return
     */
    public static boolean isEnough(ConsumablesPack cP, UnitsPack uP){
        return consumablesPack.isEnough(cP) && unitsPack.isEnough(uP);
    }

    /**
     * checks if there is enough consumables
     * @param cP
     * @return
     */
    public static boolean isEnough(ConsumablesPack cP){
        return consumablesPack.isEnough(cP);
    }

    /**
     * checks if there is enough units
     * @param uP
     * @return
     */
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
