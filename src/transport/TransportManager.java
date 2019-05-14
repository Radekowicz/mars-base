package transport;

import java.util.List;

public final class TransportManager {
    private static TransportManager transportManager = null;

    private static List<Transport> transports;

    private TransportManager(List<Transport> ts) {
        transports = ts;
    }

    public static void initializeResourcesManager(List<Transport> ts){
        if(transportManager == null)
            transportManager = new TransportManager(ts);
    }

    public static void update() {
        return;
    }
}
