package gui;

import java.awt.event.ActionListener;
import java.util.EventObject;

public interface ChangeButtonListener extends ActionListener {
    void changeButtonOccurred(EventObject event, String who);
}
