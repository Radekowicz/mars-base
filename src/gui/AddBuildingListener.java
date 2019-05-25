package gui;

import java.util.EventListener;

public interface AddBuildingListener extends EventListener {
    void AddBuildingButtonOccurred(AddBuildingButtonEvent event);
}
