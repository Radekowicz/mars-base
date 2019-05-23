package resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesManagerTest {

    @Test
    void add() {
        ResourcesManager.initializeResourcesManager(new ConsumablesPack(0,0,0,0,0,0),new UnitsPack(0,0));
        ConsumablesPack zasoby = new ConsumablesPack(100,100,100,100,100,100);
        UnitsPack jednostki = new UnitsPack(100,100);
        ResourcesManager.add(zasoby,jednostki);
        assertEquals(ResourcesManager.getEnergyStatus(),zasoby.getEnergy());
        assertEquals(ResourcesManager.getMarsMaterialStatus(),zasoby.getMarsMaterial());
        assertEquals(ResourcesManager.getEarthMaterialStatus(),zasoby.getEarthMaterial());
        assertEquals(ResourcesManager.getWaterStatus(),zasoby.getWater());
        assertEquals(ResourcesManager.getFoodStatus(),zasoby.getFood());
        assertEquals(ResourcesManager.getOxygenStatus(),zasoby.getOxygen());
        assertEquals(ResourcesManager.getHumanStatus(),jednostki.getHumans());
        assertEquals(ResourcesManager.getOxygenStatus(),jednostki.getRobots());
        ResourcesManager.zeroing();

    }

    @Test
    void add1() {
       ConsumablesPack zasoby2 = new ConsumablesPack(200,123,1235,326,2134,12);
       ResourcesManager.add(zasoby2);
        assertEquals(ResourcesManager.getEnergyStatus(),zasoby2.getEnergy());
        assertEquals(ResourcesManager.getMarsMaterialStatus(),zasoby2.getMarsMaterial());
        assertEquals(ResourcesManager.getEarthMaterialStatus(),zasoby2.getEarthMaterial());
        assertEquals(ResourcesManager.getWaterStatus(),zasoby2.getWater());
        assertEquals(ResourcesManager.getFoodStatus(),zasoby2.getFood());
        assertEquals(ResourcesManager.getOxygenStatus(),zasoby2.getOxygen());
        ResourcesManager.zeroing();

    }

    @Test
    void add2() {
        UnitsPack jednostki2 = new UnitsPack(1254125,215125);
        ResourcesManager.add(jednostki2);
        assertEquals(ResourcesManager.getHumanStatus(),jednostki2.getHumans());
        assertEquals(ResourcesManager.getRobotStatus(),jednostki2.getRobots());
        ResourcesManager.zeroing();
    }

    @Test
    void subtract() {
        ConsumablesPack zasoby3 = new ConsumablesPack(100,1232,543,98675,231,5445);
        UnitsPack jednostki3  = new UnitsPack(3255,3454);
        ResourcesManager.add(zasoby3,jednostki3);
        ResourcesManager.subtract(zasoby3,jednostki3);
        assertEquals(ResourcesManager.getEnergyStatus(),0);
        assertEquals(ResourcesManager.getMarsMaterialStatus(),0);
        assertEquals(ResourcesManager.getEarthMaterialStatus(),0);
        assertEquals(ResourcesManager.getWaterStatus(),0);
        assertEquals(ResourcesManager.getFoodStatus(),0);
        assertEquals(ResourcesManager.getOxygenStatus(),0);
        assertEquals(ResourcesManager.getHumanStatus(),0);
        assertEquals(ResourcesManager.getOxygenStatus(),0);
        ResourcesManager.zeroing();
    }

    @Test
    void subtract1() {
        ConsumablesPack zasoby4 = new ConsumablesPack(1231,3252,34534,423,42,43);
        ResourcesManager.add(zasoby4);
        ResourcesManager.subtract(zasoby4);
        assertEquals(ResourcesManager.getEnergyStatus(),0);
        assertEquals(ResourcesManager.getMarsMaterialStatus(),0);
        assertEquals(ResourcesManager.getEarthMaterialStatus(),0);
        assertEquals(ResourcesManager.getWaterStatus(),0);
        assertEquals(ResourcesManager.getFoodStatus(),0);
        assertEquals(ResourcesManager.getOxygenStatus(),0);
        ResourcesManager.zeroing();
    }

    @Test
    void subtract2() {
        UnitsPack jednostki4 = new UnitsPack(123,123);
        ResourcesManager.add(jednostki4);
        ResourcesManager.subtract(jednostki4);
        assertEquals(ResourcesManager.getRobotStatus(),0);
        assertEquals(ResourcesManager.getRobotStatus(),0);
        ResourcesManager.zeroing();

    }

    @Test
    void isEnough() {
    ConsumablesPack zasoby5 = new ConsumablesPack(100,100,100,100,100,100);
    UnitsPack jednostki5 = new UnitsPack(100,100);
    ResourcesManager.add(zasoby5,jednostki5);
    assertTrue(ResourcesManager.isEnough(zasoby5,jednostki5));
    ResourcesManager.zeroing();
    }

    @Test
    void isEnough1() {
        ConsumablesPack zasoby6 = new ConsumablesPack(100,100,100,100,100,100);
        ResourcesManager.add(zasoby6);
        assertTrue(ResourcesManager.isEnough(zasoby6));
        ResourcesManager.zeroing();

    }

    @Test
    void isEnough2() {
        UnitsPack jednostki6 = new UnitsPack(100,200);
        ResourcesManager.add(jednostki6);
        assertTrue(ResourcesManager.isEnough(jednostki6));
        ResourcesManager.zeroing();
    }

    @Test
    void getEnergyStatus() {
    ConsumablesPack zasoby7 = new ConsumablesPack(100,100,100,100,100,100);
    UnitsPack jednostki7 = new UnitsPack(100,100);
    ResourcesManager.add(zasoby7,jednostki7);
    assertEquals(ResourcesManager.getEnergyStatus(),100);
    ResourcesManager.zeroing();
    }

    @Test
    void getWaterStatus() {
        ConsumablesPack zasoby8 = new ConsumablesPack(100,100,100,546,100,100);
        UnitsPack jednostki8 = new UnitsPack(100,100);
        ResourcesManager.add(zasoby8,jednostki8);
        assertEquals(ResourcesManager.getWaterStatus(),546);
        ResourcesManager.zeroing();
    }

    @Test
    void getOxygenStatus() {
        ConsumablesPack zasoby9 = new ConsumablesPack(100,546,100,100,100,1902);
        UnitsPack jednostki9 = new UnitsPack(100,100);
        ResourcesManager.add(zasoby9,jednostki9);
        assertEquals(ResourcesManager.getOxygenStatus(),1902);
        ResourcesManager.zeroing();
    }

    @Test
    void getFoodStatus() {
        ConsumablesPack zasoby10 = new ConsumablesPack(100,546,1902,100,3300,100);
        UnitsPack jednostki10 = new UnitsPack(100,100);
        ResourcesManager.add(zasoby10,jednostki10);
        assertEquals(ResourcesManager.getFoodStatus(),3300);
        ResourcesManager.zeroing();
    }

    @Test
    void getMarsMaterialStatus() {
        ConsumablesPack zasoby11 = new ConsumablesPack(100,1980,1902,100,3300,100);
        UnitsPack jednostki11 = new UnitsPack(100,100);
        ResourcesManager.add(zasoby11,jednostki11);
        assertEquals(ResourcesManager.getMarsMaterialStatus(),1980);
        ResourcesManager.zeroing();
    }

    @Test
    void getEarthMaterialStatus() {
        ConsumablesPack zasoby12 = new ConsumablesPack(100,1980,45554,100,3300,100);
        UnitsPack jednostki12 = new UnitsPack(100,100);
        ResourcesManager.add(zasoby12,jednostki12);
        assertEquals(ResourcesManager.getEarthMaterialStatus(),45554);
        ResourcesManager.zeroing();
    }

    @Test
    void getHumanStatus() {
        ConsumablesPack zasoby13 = new ConsumablesPack(100,2378,1902,100,3300,100);
        UnitsPack jednostki13 = new UnitsPack(9023,100);
        ResourcesManager.add(zasoby13,jednostki13);
        assertEquals(ResourcesManager.getHumanStatus(),9023);
        ResourcesManager.zeroing();
    }

    @Test
    void getRobotStatus() {
        ConsumablesPack zasoby14 = new ConsumablesPack(100,2378,1902,100,3300,100);
        UnitsPack jednostki14 = new UnitsPack(100,7654);
        ResourcesManager.add(zasoby14,jednostki14);
        assertEquals(ResourcesManager.getRobotStatus(),7654);
        ResourcesManager.zeroing();
    }
}