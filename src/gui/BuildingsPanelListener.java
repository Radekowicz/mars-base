package gui;

import java.util.EventListener;

public interface BuildingsPanelListener extends EventListener {
    void BuildingPanelOccurred(BuildingButtonEvent event);
}
