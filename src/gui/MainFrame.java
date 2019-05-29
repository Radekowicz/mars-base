package gui;

import events.DescriptionPublisher;
import events.EventListener;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame, stores all panels and switch between actions panels
 */
public class MainFrame extends JFrame implements EventListener {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1024;

    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private TransportsPanel transportsPanel;
    private EventPanel eventPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    /**
     * Creates main frame which consist of {@link MainFrame#resourcesPanel},{@link MainFrame#buildingsPanel}, {@link MainFrame#transportsPanel}, {@link MainFrame#eventPanel} and {@link MainFrame#bottomPanel}
     */
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

        buildingsPanel.setAddBuildingListener(new AddBuildingListener() {
            @Override
            public void AddBuildingButtonOccurred(AddBuildingButtonEvent event) {
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

        transportsPanel.setOrderTransportListener(new OrderTransportListener() {
            @Override
            public void orderTransportButtonOccurred(OrderTransportButtonEvent event) {
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

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Uses by classes implements {@link DescriptionPublisher}. When event is occurred {@link MainFrame#eventPanel} shows up
     * @param descriptionPublisher object which may occurred event
     */
    @Override
    public void eventOccurred(DescriptionPublisher descriptionPublisher) {
        eventPanel.setInformation(descriptionPublisher.getDescribed());
        eventPanel.setVisible(true);
        eventPanel.validate();
        eventPanel.repaint();
        buildingsPanel.refreshAll();
        transportsPanel.refreshAll();
    }
}
