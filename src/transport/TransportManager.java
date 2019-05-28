package transport;

import events.EventListener;
import resources.ConsumablesPack;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class managing transport,
 */
public final class TransportManager {
    private static TransportManager transportManager = null;

    private static List<Transport> transports;
    private static int transportOrderBreak;
    private static int currentTransportOrderBreak;
    private static EventListener eventListener;

    /**
     * Sets eventListener
     * @param eventListener EventListener
     */
    public static void setEventListener(EventListener eventListener) {
        TransportManager.eventListener = eventListener;
    }

    /**
     * Sets List of Transport and transportBreakOrder from parms, and sets currentTransportOrderBreak from transportBreakOrder
     * TransportOrderBreak is constant value and first order break equals to that value
     * @param ts List of Transport
     * @param tOB time order break
     */
    private TransportManager(List<Transport> ts, int tOB) {
        transports = ts;
        transportOrderBreak = tOB;
        currentTransportOrderBreak = transportOrderBreak;
    }

    /**
     * Initializes ResourcesManager (which cannot have class's instance), and sets all means of transport in base
     * @param ts List of Transport
     * @param transportOrderBreak timeOrderBreak
     */
    public static void initializeResourcesManager(List<Transport> ts, int transportOrderBreak){
        if(transportManager == null)
            transportManager = new TransportManager(ts, transportOrderBreak);

        for (Transport transport : transports) {
            transport.setInBase();
        }
    }

    /**
     * Updates all means of transport in list depending on its status, if transport have status loaded is load, unloaded - unload, on_the_way - move, destroyed - add to new list (destroyedTransport). Transport in that list will be remove. At the end decreases currentTransportOrderBreak.
     */
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

        if (currentTransportOrderBreak != 0)
            --currentTransportOrderBreak;
    }

    /**
     * Checks that transport is in base, if is, use send() method on transport and returns value from that method
     * @param transport transport from list
     * @param target target
     * @return true if transport have been sent, other wise false
     */
    public static boolean send(Transport transport, Place target) {
        if (transport.getTransportStatus() != TransportStatus.WAITING)
            return false;

        return transport.send(target, determineDistance(target));
    }

    /**
     * Compares currentTransportOrderBreak to zero and return value
     * @return true if order is possible, otherwise false
     */
    private static boolean orderIsPossible() {
        return currentTransportOrderBreak == 0;
    }

    /**
     * Check that is order possible. If is, compare transport instance to
     * @param transport class name name of the transport class
     * @return true if the order has been processed, otherwise false
     * @deprecated Now in use is {@link TransportManager#orderTransport(Transport)}
     *  }
     */
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


    /**
     * Checks that order is possible, if is add transport to list of means of transport
     * @param transport transport
     * @return true if transport have been added (ordered) to list, otherwise false
     */
    public static boolean orderTransport(Transport transport) {
        if (!orderIsPossible())
            return false;

        transports.add(transport);

        return true;
    }

    /**
     * Returns reference to list of transport
     * @return {@link TransportManager#transports}
     */
    public static List<Transport> getTransports() {
        return transports;
    }

    /**
     * Generate consumables resources in depends of place.
     * @param place place where resources are generate
     * @return drawn ConsumablesPack
     */
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

    /**
     * Generate consumables resources in depends of place
     * @param place place where resources are generate
     * @return drawn UnitsPack
     */
    public static UnitsPack generateUnits(Place place) {
        if (place != Place.EARTH)
            return new UnitsPack(0,0);

        Random random = new Random();
        return new UnitsPack(random. nextInt(11) + 5,0);
    }

    /**
     * Generate value of distance between Base and other place
     * @param place place where resources are generate
     * @return drawn UnitsPack
     */
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
