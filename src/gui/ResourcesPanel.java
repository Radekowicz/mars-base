package gui;

import buildings.BuildingManager;
import resources.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourcesPanel extends JPanel {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 34;

    private JLabel energyArea;
    private JLabel waterArea;
    private JLabel oxygenArea;
    private JLabel foodArea;
    private JLabel marsMaterialArea;
    private JLabel earthMaterialArea;

    private JLabel humanArea;
    private JLabel maxCapacityHumanArea;
    private JLabel robotArea;

    public ResourcesPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        energyArea = new JLabel("" + ResourcesManager.getEnergyStatus());
        waterArea = new JLabel ("" + ResourcesManager.getWaterStatus());
        oxygenArea = new JLabel("" + ResourcesManager.getOxygenStatus());
        foodArea = new JLabel("" + ResourcesManager.getFoodStatus());
        marsMaterialArea = new JLabel("" + ResourcesManager.getMarsMaterialStatus());
        earthMaterialArea = new JLabel("" + ResourcesManager.getEarthMaterialStatus());
        humanArea = new JLabel("" + ResourcesManager.getHumanStatus());
        maxCapacityHumanArea = new JLabel("of " + BuildingManager.getMaxHumanCapacity());
        robotArea = new JLabel("" + ResourcesManager.getRobotStatus());

        add(new JLabel("Resources: "));
        add(new JLabel("Energy: "));
        add(energyArea);
        add(new JLabel("Oxygen:"));
        add(oxygenArea);
        add(new JLabel("Water:"));
        add(waterArea);
        add(new JLabel("Food:"));
        add(foodArea);
        add(new JLabel("Earth material:"));
        add(earthMaterialArea);
        add(new JLabel("Mars material:"));
        add(marsMaterialArea);

        add(new JLabel("Units:"));
        add(new JLabel("Human:"));
        add(humanArea);
        add(maxCapacityHumanArea);
        add(new JLabel("Robot:"));
        add(robotArea);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateResourcesPanel();
            }
        });

        timer.start();
    }

    private void updateResourcesPanel() {
        energyArea.setText("" + ResourcesManager.getEnergyStatus());
        waterArea.setText("" + ResourcesManager.getWaterStatus());
        oxygenArea.setText("" + ResourcesManager.getOxygenStatus());
        foodArea.setText("" + ResourcesManager.getFoodStatus());
        marsMaterialArea.setText("" + ResourcesManager.getMarsMaterialStatus());
        earthMaterialArea.setText("" + ResourcesManager.getEarthMaterialStatus());
        humanArea.setText("" + ResourcesManager.getHumanStatus());
        maxCapacityHumanArea = new JLabel("of " + BuildingManager.getMaxHumanCapacity());
        robotArea.setText("" + ResourcesManager.getRobotStatus());
    }
}
