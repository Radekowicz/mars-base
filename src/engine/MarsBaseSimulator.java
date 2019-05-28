package engine;

import buildings.BuildingManager;
import events.EventManager;
import gui.MainFrame;
import resources.ResourcesManager;
import transport.TransportManager;

import java.awt.*;

public final class MarsBaseSimulator implements Runnable {

    private MainFrame mainFrame;
    private long gameSpeed;

    /**
     * The most important classes are initiated in the constructor by using SettingsManager class.
     * Next step is assignment events.EventListener to classes which implement that interface and run MainFrame by add it to EventQueue
     */
    public MarsBaseSimulator() {
        long loadedGameSpeed = SettingsStream.loadGameSpeed();
        if (loadedGameSpeed < 0)
            gameSpeed = 1000 * loadedGameSpeed;
        else
            gameSpeed = 1000 / loadedGameSpeed;
        SettingsManager.initializeSimulator();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new MainFrame();
                EventManager.setEventListener(mainFrame);
                TransportManager.setEventListener(mainFrame);
            }
        });
    }

    /**
     * That method represents main loop which is responds for update game's status and speed
     */
    public void run() {
        while (true) {
            BuildingManager.update();
            TransportManager.update();
            EventManager.tryTriggerEvent();
            ResourcesManager.update();
            try {
                Thread.sleep(gameSpeed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
