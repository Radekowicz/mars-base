import engine.MarsBaseSimulator;
import gui.MainFrame;
import transport.Transport;
import transport.TransportManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        MarsBaseSimulator marsBaseSimulator = new MarsBaseSimulator();

        EventQueue.invokeLater(() -> {
            new MainFrame();
        });

        for (Transport transport: TransportManager.getTransports())
            System.out.println(transport.getName());

        marsBaseSimulator.run();
    }
}
