package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1024;

    public MainFrame() {
        super("Info Panel");
        System.out.println("HFUENFUIEBNIU");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        add(new ResourcesPanel(), BorderLayout.NORTH);
        add(new TextField(), BorderLayout.CENTER);
        pack();
    }
}
