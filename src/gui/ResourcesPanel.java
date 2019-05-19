package gui;

import resources.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourcesPanel extends JPanel {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 34;

    private JTextArea energyArea;
    private JTextArea waterArea;
    private JTextArea oxygenArea;
    private JTextArea foodArea;
    private JTextArea marsMaterialArea;
    private JTextArea earthMaterialArea;

    private JTextArea humanArea;
    private JTextArea robotArea;

    public ResourcesPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        energyArea = new JTextArea("" + ResourcesManager.getEnergyStatus());
        waterArea = new JTextArea ("" + ResourcesManager.getWaterStatus());
        oxygenArea = new JTextArea("" + ResourcesManager.getOxygenStatus());
        foodArea = new JTextArea("" + ResourcesManager.getFoodStatus());
        marsMaterialArea = new JTextArea("" + ResourcesManager.getMarsMaterialStatus());
        earthMaterialArea = new JTextArea("" + ResourcesManager.getEarthMaterialStatus());
        humanArea = new JTextArea("" + ResourcesManager.getHumanStatus());
        robotArea = new JTextArea("" + ResourcesManager.getRobotStatus());

        add(new JTextArea("Resources: "));
        add(new JTextArea("Energy: "));
        add(energyArea);
        add(new JTextArea("Oxygen:"));
        add(oxygenArea);
        add(new JTextArea("Water:"));
        add(waterArea);
        add(new JTextArea("Food:"));
        add(foodArea);
        add(new JTextArea("Earth material:"));
        add(earthMaterialArea);
        add(new JTextArea("Mars material:"));
        add(marsMaterialArea);

        add(new JTextArea("Units:"));
        add(new JTextArea("Human:"));
        add(humanArea);
        add(new JTextArea("Robot:"));
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
        robotArea.setText("" + ResourcesManager.getRobotStatus());
    }
}
