import engine.MarsBaseSimulator;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        MarsBaseSimulator marsBaseSimulator = new MarsBaseSimulator();

        EventQueue.invokeLater(() -> {
            JFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        marsBaseSimulator.run();
    }
}
