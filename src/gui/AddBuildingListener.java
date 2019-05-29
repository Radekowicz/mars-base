package gui;

import java.util.EventListener;

/**
 * Listens to pressing AddBuildingButton
 */
public interface AddBuildingListener extends EventListener {
    void AddBuildingButtonOccurred(AddBuildingButtonEvent event);
}
