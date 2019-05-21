package engine;

import buildings.BuildingManager;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

public final class MarsBaseSimulator {

    // tymczasowo, bez uzupełnionej pętli z budynkami i transportem takie zastępstwo na sprawdzenie czy działa
    private ConsumablesPack CP = new ConsumablesPack(12, 13, 14, 15, 16, 17);
    private UnitsPack UP = new UnitsPack(21, 22);

    public MarsBaseSimulator() {
        SettingsManager.initializeSimulator();
    }

    public void run() {
        while (true) {
            BuildingManager.update();
            ResourcesManager.showRecources();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
