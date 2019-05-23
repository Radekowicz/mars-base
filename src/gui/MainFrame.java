package gui;

import events.Event;
import events.EventListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements EventListener {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1024;

    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private TransportsPanel transportsPanel;
    private EventPanel eventPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    public MainFrame() {
        super("Mars base simulator");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());

        resourcesPanel = new ResourcesPanel();
        buildingsPanel = new BuildingsPanel();
        transportsPanel = new TransportsPanel();
        eventPanel = new EventPanel();
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
        topPanel.add(eventPanel);
        bottomPanel.add(new JButton("TEMPORARY BUTTON DOES NOTHIGN"));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void eventOccurred(Event event) {
        eventPanel.setInformation(event.getDescribed());
        eventPanel.setVisible(true);
        eventPanel.validate();
        eventPanel.repaint();
    }
}
