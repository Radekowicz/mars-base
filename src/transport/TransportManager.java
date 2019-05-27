package transport;

import events.EventListener;
import resources.ConsumablesPack;
import resources.UnitsPack;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class TransportManager {
    private static TransportManager transportManager = null;

    private static List<Transport> transports;
    private static int transportOrderBreak;
    private static int currentTransportOrerBreak;
    private static EventListener eventListener;

    public static void setEventListener(EventListener eventListener) {
        TransportManager.eventListener = eventListener;
    }

    private TransportManager(List<Transport> ts, int tOB) {
        transports = ts;
        transportOrderBreak = tOB;
        currentTransportOrerBreak = transportOrderBreak;
    }

    public static void initializeResourcesManager(List<Transport> ts, int transportOrderBreak){
        if(transportManager == null)
            transportManager = new TransportManager(ts, transportOrderBreak);

        for (Transport transport : transports) {
            transport.setInBase();
        }
    }

    public static void update() {
        List<Transport> destroyedTransport = new ArrayList<>();

        for (Transport transport: transports) {
            switch (transport.getTransportStatus()) {
                case LOADED:
                    transport.load(generateResources(transport.getTarget()), generateUnits(transport.getTarget()));
                    break;
                case UNLOADED:
                    transport.unload(eventListener);
                    break;
                case ON_THE_WAY:
                    transport.move();
                    break;
                case DESTROYED:
                    destroyedTransport.add(transport);
                    break;
            }
        }

        for (Transport transport: destroyedTransport)
            transports.remove(transport);

        destroyedTransport.clear();

        if (currentTransportOrerBreak != 0)
            --currentTransportOrerBreak;
    }

    public static boolean send(Transport transport, Place target) {
        if (transport.getTransportStatus() != TransportStatus.WAITING)
            return false;

        return transport.send(target, determineDistance(target));
    }

    private static boolean orderIsPossible() {
        return currentTransportOrerBreak == 0;
    }

    public static boolean orderTransport(String transport) {
        if (!orderIsPossible())
            return false;

        boolean ordered = false;

        switch (transport) {
            case "BFRRocket":
                transports.add(new BFRocket());
                ordered = true;
                break;
            case "SwiftRocket":
                transports.add(new SwiftRocket());
                ordered = true;
                break;
            case "Truck":
                transports.add(new Truck());
                ordered = true;
                break;
            default:
                break;
        }

        return ordered;
    }

    public static List<Transport> getTransports() {
        return transports;
    }

    public static ConsumablesPack generateResources(Place place) {
        Random random = new Random();

        if (place == Place.MARS) {
            long water = random.nextInt(10);
            long marsMaterial = random.nextInt(150) + 30;

            return new ConsumablesPack(0,marsMaterial,0,water,0,0);
        }
        else if (place == Place.EARTH) {
            long earthMaterial = random.nextInt(10);

            return new ConsumablesPack(0,0,earthMaterial,0,0,0);
        }
        else if (place == Place.ASTEROID) {
            long water = random.nextInt(3000);
            long marsMaterial = random.nextInt(500) + 50;
            long earthMaterial = random.nextInt(300);

            return new ConsumablesPack(0,marsMaterial,earthMaterial,water,0,0);
        }
        else if (place == Place.PLANETOID) {
            long water = random.nextInt(10000) + 2000;
            long marsMaterial = random.nextInt(150) + 30;
            long earthMaterial = random.nextInt(300);

            return new ConsumablesPack(0,marsMaterial,earthMaterial,water,0,0);
        }

        return new ConsumablesPack(0,0,0,0,0,0);
    }

    public static UnitsPack generateUnits(Place place) {
        if (place != Place.EARTH)
            return new UnitsPack(0,0);

        Random random = new Random();
        return new UnitsPack(random. nextInt(11) + 5,0);
    }

    public static int determineDistance(Place place) {
        Random random = new Random();
        int distance;

        switch (place) {
            case MARS:
                distance = random.nextInt(10000) + 1000;
                break;
            case EARTH:
                distance = random.nextInt(2000000) + 56000;
                break;
            case ASTEROID:
                distance = random.nextInt(50000) + 10000;
                break;
            case PLANETOID:
                distance = random.nextInt(10000) + 50000;
                break;
            default:
                distance = 1;
                break;
        }

        return distance;
    }
}
