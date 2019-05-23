package transport;

import java.util.List;

public final class TransportManager {
    private static TransportManager transportManager = null;

    private static List<Transport> transports;
//    private static int transportOrderBreak =
//    private static int

    private TransportManager(List<Transport> ts) {
        transports = ts;
    }
    private TransportManager(List<Transport> ts, int tOB) {
        transports = ts;
//        transportOrderBreak = tOB;
    }

    public static void initializeResourcesManager(List<Transport> ts){
        if(transportManager == null)
            transportManager = new TransportManager(ts);
    }

    public static void initializeResourcesManager(List<Transport> ts, int transportOrderBreak){
        if(transportManager == null)
            transportManager = new TransportManager(ts);
    }

//    public static void update() {
//
//        for (Transport transport: transports) {
//
//        }
//
//        if ()
//    }

//    public static void send(Transport transport, Place target) {
//        if (transport.get)
//
//
//    }

//    private static boolean orderIsPossible() {
//        return
//    }

//    public static boolean orderTransport(String transport) {
//        if (!orderIsPossible())
//            return false;
//
//
//    }

    public static List<Transport> getTransports() {
        return transports;
    }
}
