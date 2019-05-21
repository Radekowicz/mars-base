package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1024;

    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private TransportsPanel transportsPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    public MainFrame() {
        super("Mars base simulator");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());

        resourcesPanel = new ResourcesPanel();
        buildingsPanel = new BuildingsPanel();
        transportsPanel = new TransportsPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        buildingsPanel.setBuildingsPanelListener(new BuildingsPanelListener() {
            @Override
            public void BuildingPanelOccurred(BuildingButtonEvent event) {
                bottomPanel.removeAll();
                bottomPanel.add(event.getPanel());
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
        });

        transportsPanel.setTransportsPanelListener(new TransportsPanelListener() {
            @Override
            public void TransportPanelOccurred(TransportButtonEvent event) {
                bottomPanel.removeAll();
                bottomPanel.add(event.getPanel());
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
        });

        topPanel.add(resourcesPanel);
        topPanel.add(buildingsPanel);
        topPanel.add(transportsPanel);
        bottomPanel.add(new JButton("TEMPORARY BUTTON DOES NOTHIGN"));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
