package gui;

import java.util.EventObject;

public class AddBuildingButtonEvent extends EventObject {
    public AddBuildingButtonEvent(Object source) {
        super(source);
    }

     public AddBuildingPanel getPanel() {
        return new AddBuildingPanel();
     }
}
