package gui;

import buildings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBuildingPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 200;
    private JComboBox buildingsComboBox;
    private JButton buildButton;
    private JPanel resultInfoPanel;

    public AddBuildingPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buildingsComboBox = new JComboBox();
        buildingsComboBox.setPreferredSize(new Dimension(50, 30));
        buildingsComboBox.addItem("Hub");
        buildingsComboBox.addItem("Cold fusion power plant");
        buildingsComboBox.addItem("Greenhouse");
        buildingsComboBox.addItem("Open pit mine");
        buildingsComboBox.addItem("Oxygen generator");
        buildingsComboBox.addItem("Print station");
        buildingsComboBox.addItem("Robot station");
        buildingsComboBox.addItem("Solar panel");

        add(buildingsComboBox);

        buildButton = new JButton("Build");
        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;

                switch (buildingsComboBox.getSelectedIndex()) {
                    case 0:
                        result = BuildingManager.addBuilding(new Hub());
                        break;
                    case 1:
                        result = BuildingManager.addBuilding(new ColdFusionPowerPlant());
                        break;
                    case 2:
                        result = BuildingManager.addBuilding(new Greenhouse());
                        break;
                    case 3:
                        result = BuildingManager.addBuilding(new OpenPitMine());
                        break;
                    case 4:
                        result = BuildingManager.addBuilding(new OxygenGenerator());
                        break;
                    case 5:
                        result = BuildingManager.addBuilding(new PrintStation());
                        break;
                    case 6:
                        result = BuildingManager.addBuilding(new RobotStation());
                        break;
                    case 7:
                        result = BuildingManager.addBuilding(new SolarPanel());
                        break;
                    default:
                        break;
                }


                resultInfoPanel.removeAll();
                String info = result ? "Building have been added." : "You cannot build this building, no enough resources";
                resultInfoPanel.add(new JLabel(info));
                resultInfoPanel.validate();
                resultInfoPanel.repaint();

            }
        });

        add(buildButton);

        resultInfoPanel = new JPanel();
        add(resultInfoPanel);
    }
}
