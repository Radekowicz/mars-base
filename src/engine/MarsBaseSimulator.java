package engine;

import buildings.BuildingManager;
import events.Event;
import events.EventManager;
import gui.MainFrame;

import java.awt.*;

public final class MarsBaseSimulator {
    private MainFrame mainFrame;

    public MarsBaseSimulator() {
        SettingsManager.initializeSimulator();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new MainFrame();
                EventManager.setEventListener(mainFrame);
            }
        });
    }

    public void run() {
        while (true) {
            BuildingManager.update();
            //TransportManager.update();
            EventManager.tryTriggerEvent();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
