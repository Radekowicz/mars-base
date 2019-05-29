package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel which shows event messages
 */
public class EventPanel extends JPanel {
    private JLabel information;
    private JButton hidePanelButton;

    public EventPanel() {
        information = new JLabel("Prepare yourself best as you can. For any time event can happen!");
        hidePanelButton = new JButton("Hide");
        hidePanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(information);
        add(hidePanelButton);
    }

    public void setInformation(String information) {
        this.information.setText(information);
    }
}

