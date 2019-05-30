package gui;

import java.util.EventListener;

/**
 * Listeners which listens changes in TransportsPanel
 */
public interface TransportsPanelListener extends EventListener {
    void TransportPanelOccurred(TransportButtonEvent event);
}
