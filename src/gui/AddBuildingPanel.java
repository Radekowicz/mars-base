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
    Building newBuilding;
    boolean lastResult;
    NewItemListener newItemListener;

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

                switch (buildingsComboBox.getSelectedIndex()) {
                    case 0:
                        newBuilding = new Hub();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 1:
                        newBuilding = new ColdFusionPowerPlant();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 2:
                        newBuilding = new Greenhouse();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 3:
                        newBuilding = new OpenPitMine();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 4:
                        newBuilding = new OxygenGenerator();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 5:
                        newBuilding = new PrintStation();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 6:
                        newBuilding = new RobotStation();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    case 7:
                        newBuilding = new SolarPanel();
                        lastResult = BuildingManager.addBuilding(newBuilding);
                        break;
                    default:
                        break;
                }


                resultInfoPanel.removeAll();
                String info;
                if (lastResult) {
                    info = "Building have been added.";
                    System.out.println("NIL: " + newItemListener);
                    System.out.println("NB: " + newBuilding.getName());
                    newItemListener.newItemAdded(newBuilding);
                    lastResult = false;
                }
                else {
                    info = "You cannot build this building, no enough resources";
                }

                resultInfoPanel.add(new JLabel(info));
                resultInfoPanel.validate();
                resultInfoPanel.repaint();
            }
        });

        add(buildButton);

        resultInfoPanel = new JPanel();
        add(resultInfoPanel);
    }

    public void setNewItemListener(NewItemListener newItemListener) {
        this.newItemListener = newItemListener;
    }
}
