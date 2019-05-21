package engine;

import java.io.*;
import java.util.*;

import buildings.*;
import resources.ConsumablesPack;
import resources.UnitsPack;

public final class SettingsStream {
    private SettingsStream() {}

    public static ConsumablesPack loadConsumablesPack() {
        File file = new File("Options/CP.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            long[] values = new long[6];

            for (int i = 0; i < 6; ++i) {
                values[i] = Long.parseLong(bufferedReader.readLine());
            }

            return new ConsumablesPack(values[0], values[1], values[2], values[3], values[4], values[5]);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ConsumablesPack(100, 100, 100, 100, 100, 100);
    }

    public static UnitsPack loadUnitsPack() {
        File file = new File("Options/UP.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            long[] values = new long[2];

            for (int i = 0; i < 2; ++i) {
                values[i] = Long.parseLong(bufferedReader.readLine());
            }

            return new UnitsPack(values[0], values[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new UnitsPack(100, 300);
    }

    public static ArrayList<Building> loadBuildings() {
        File file = new File("Options/Buildings.txt");
        ArrayList<Building> buildings = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String buildingName;

            while ((buildingName = bufferedReader.readLine()) != null) {
                switch (buildingName) {
                    case "Hub":
                        buildings.add(new Hub());
                        break;
                    case "Greenhouse":
                        buildings.add(new Greenhouse());
                        break;
                    case "ColdFusionPowerPlant":
                        buildings.add(new ColdFusionPowerPlant());
                        break;
                    case "OpenPitMine":
                        buildings.add(new OpenPitMine());
                        break;
                    case "OxygenGenerator":
                        buildings.add(new OxygenGenerator());
                        break;
                    case "PrintStation":
                        buildings.add(new PrintStation());
                        break;
                    case "RobotStation":
                        buildings.add(new RobotStation());
                        break;
                    case "RocketStation":
                        buildings.add(new RobotStation());
                        break;
                    case "SolarPanel":
                        buildings.add(new SolarPanel());
                        break;
                    default:
                        break;
                }
            }

            return buildings;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buildings;
    }

    public static int loadChanceForEvent() {
        File file = new File("Options/EventOptions.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            return Integer.parseInt(bufferedReader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 70;
    }

    public static int loadTimeBreakForEvent() {
        File file = new File("Options/EventOptions.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            bufferedReader.readLine();
            return Integer.parseInt(bufferedReader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 10000;
    }
}
