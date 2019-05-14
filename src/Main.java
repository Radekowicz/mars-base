import engine.MarsBaseSimulator;
import gui.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

        MarsBaseSimulator marsBaseSimulator = new MarsBaseSimulator();
        marsBaseSimulator.run();
    }
}
