package gui;

import java.util.EventListener;

/**
 * Listeners which listens changes in BuildingsPanel
 */
public interface BuildingsPanelListener extends EventListener {
    void BuildingPanelOccurred(BuildingButtonEvent event);
}
