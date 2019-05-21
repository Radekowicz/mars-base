package engine;

import buildings.BuildingManager;
import events.EventManager;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;
import transport.Transport;
import transport.TransportManager;

public final class MarsBaseSimulator {

    public MarsBaseSimulator() {
        SettingsManager.initializeSimulator();
    }

    public void run() {
        while (true) {
            BuildingManager.update();
            TransportManager.update();
            EventManager.tryTriggerEvent();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
