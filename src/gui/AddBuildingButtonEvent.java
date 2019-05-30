package gui;


import java.util.EventObject;

/**
 * Event object that informs the main panel about the change of the action panel
 */
public class AddBuildingButtonEvent extends EventObject {
    NewItemListener newItemListener;

    public AddBuildingButtonEvent(Object source) {
        super(source);
    }

    public AddBuildingButtonEvent(Object source, NewItemListener newItemListener) {
        super(source);
        this.newItemListener = newItemListener;
    }

    public AddBuildingPanel getPanel() {
         AddBuildingPanel addBuildingPanel = new AddBuildingPanel();
         addBuildingPanel.setNewItemListener(newItemListener);
         return addBuildingPanel;
     }

    public void setNewItemListener(NewItemListener newItemListener) {
        this.newItemListener = newItemListener;
    }
}
