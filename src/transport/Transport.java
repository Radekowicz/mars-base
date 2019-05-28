package transport;

import events.DescriptionPublisher;
import events.Destructible;
import events.EventListener;
import events.Fixable;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Lorem ipsum set dolores ames...
 */
public abstract class Transport implements Destructible, Fixable, DescriptionPublisher {
    private int speed;
    private int orderedSpeed;
    private String name;
    private ConsumablesPack maxCPCapacity;
    private UnitsPack maxUPCapacity;
    private ConsumablesPack currentCP;
    private UnitsPack currentUP;
    private TransportStatus transportStatus;
    private Place currentPlace;
    private Place target;
    private int distanceToDestiny;
    private int distanceToReturn;
    private boolean returning;
    private List<Place> possibleTargets;


    /**
     *
     * @param speed - a number that represents how much an object can be moved
     * @param name - is created by inherited classes from the formula CLASS_NAME + NUMBER ORDER TRANSPORT
     * @param maxCPCapacity - max consumable resources capacity
     * @param maxUPCapacity - max units capacity
     * @param possibleTargets - possible places where transport can go
     */

    public Transport(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity, List<Place> possibleTargets) {
        this.speed = speed;
        this.name = name;
        this.maxCPCapacity = maxCPCapacity;
        this.maxUPCapacity = maxUPCapacity;
        this.currentCP = new ConsumablesPack(0,0,0,0,0,0);
        this.currentUP = new UnitsPack(0,0);
        this.possibleTargets = possibleTargets;
        this.transportStatus = TransportStatus.ORDERED;
        this.orderedSpeed = 490000;
        this.currentPlace = Place.SPACE;
        this.target = Place.BASE;
        this.distanceToDestiny = TransportManager.determineDistance(Place.EARTH);
        returning = true;
    }

    /**
     *
     * @param target - place to which transport is sending
     * @param distanceToDestiny - number represents distance
     * @return true if transport have been sent, otherwise false
     */
    public boolean send(Place target, int distanceToDestiny) {
        if (distanceToDestiny <= 0)
            throw new IllegalArgumentException("distance must be positive");

        if (!targetIsPossible(target))
            return false;

        if (!ResourcesManager.subtract(requiredResourcesToStart()))
            return false;

        this.target = target;
        this.distanceToDestiny = distanceToDestiny;
        this.distanceToReturn = distanceToDestiny;
        this.transportStatus = TransportStatus.ON_THE_WAY;
        this.returning = false;
        return true;
    }

    /**
     * If transport have status "ONT THE WAY" may be moved by SPEED units to distance
     * If target will be achieved transport status is change to "LOAD' or "UNLOAD" - depend on whether returning is true or false
     */
    public void move() {
        if ((distanceToDestiny - speed) <= 0) {
            distanceToDestiny = 0;
            currentPlace = target;
            if (returning) {
                transportStatus = TransportStatus.UNLOADED;
            }
            else {
                transportStatus = TransportStatus.LOADED;
            }
        }

        if (transportStatus == TransportStatus.ORDERED)
            distanceToDestiny -= orderedSpeed;
        else
            distanceToDestiny -= speed;
    }

    /**
     * If there are too many loaded units, transport is loading to max capacity, the rest is skipped
     * At the end loading, transport's status is change to ON_THE_WAY, and returning is true
     * @param CP - Consumables resources
     * @param UP - Units resources
     */
    public void load(ConsumablesPack CP, UnitsPack UP) {
        if (CP.getWater() > maxCPCapacity.getWater())
            this.currentCP.addWater(maxCPCapacity.getWater());
        else
            this.currentCP.addWater(CP.getWater());

        if (CP.getFood() > maxCPCapacity.getFood())
            this.currentCP.addFood(maxCPCapacity.getFood());
        else
            this.currentCP.addFood(CP.getFood());

        if (CP.getEarthMaterial() > maxCPCapacity.getEarthMaterial())
            this.currentCP.addEarthMaterial(maxCPCapacity.getEarthMaterial());
        else
            this.currentCP.addEarthMaterial(CP.getEarthMaterial());

        if (CP.getMarsMaterial() > maxCPCapacity.getMarsMaterial())
            this.currentCP.addMarsMaterial(maxCPCapacity.getMarsMaterial());
        else
            this.currentCP.addMarsMaterial(CP.getMarsMaterial());

        if (UP.getHumans() > maxUPCapacity.getHumans())
            this.currentUP.addHumans(maxUPCapacity.getHumans());
        else
            this.currentUP.addHumans(UP.getHumans());

        this.transportStatus = TransportStatus.ON_THE_WAY;
        this.currentPlace = Place.SPACE;
        this.target = Place.BASE;
        this.distanceToDestiny = distanceToReturn;
        this.returning = true;
    }

    /**
     * Add resources to total pot by ResourcesManager
     * @param eventListener - listener which will be notified of the return of the transport
     */
    public void unload(EventListener eventListener) {
        eventListener.eventOccurred(this);
        ResourcesManager.add(currentCP, currentUP);
        this.currentCP.zeroing();
        this.currentUP.zeroing();
        this.transportStatus = TransportStatus.WAITING;
        this.currentPlace = target;
        returning = false;
    }

    /**
     * @return transport's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return current target
     */
    public Place getTarget() {
        return target;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    /**
     * Check that target is in possibleTargets
     * @param target - place where the transport would be sent
     * @return true if target is possible, otherwise false
     */
    public boolean targetIsPossible(Place target) {
        return possibleTargets.contains(target);
    }

    /**
     * Implements of Destructible interface and carries out the damage process
     * Possible effects are: nothing if drawn number is 0, damaged if number if in range (0, 100) and destroyed if number is 100
     */
    @Override
    public void damage() {
        if (transportStatus != TransportStatus.WAITING)
            return;

        Random random = new Random();
        int levelOfDamage = random.nextInt(101);

        if (levelOfDamage == 0) {
            return;
        }
        else if (levelOfDamage == 100 || transportStatus == TransportStatus.DAMAGED) {
            transportStatus = TransportStatus.DESTROYED;
        }

        transportStatus = TransportStatus.DAMAGED;
    }

    /**
     *
     * @return true if transport have been fixed, otherwise false
     */
    @Override
    public boolean fix() {
        if (transportStatus != TransportStatus.DAMAGED) {
            return false;
        }

        if (ResourcesManager.subtract(costOfFix())) {
            transportStatus = TransportStatus.ON_THE_WAY.WAITING;
            return true;
        }

        return false;
    }

    public String getTransportStatusAsString() {
        switch (transportStatus) {
            case WAITING:
                return "waiting";
            case DAMAGED:
                return "damaged";
            case LOADED:
                return "loading";
            case UNLOADED:
                return "unloading";
            case ON_THE_WAY:
                return "on the way";
            case ORDERED:
                return "ordered";
            default:
                return "unknown transport status";
        }
    }

    public TransportStatus getTransportStatus() {
        return transportStatus;
    }

    public int getDistanceToDestiny() {
        return distanceToDestiny;
    }

    public List<Place> getPossibleTargets() {
        return possibleTargets;
    }

    @Override
    public String getDescribed() {
        return name + " came back! It brought with itself " + currentCP.toString() + " and " + currentUP.toString();
    }

    public boolean isReturning() {
        return returning;
    }

    public ArrayList<String> getPossibleTargetsAsStringList() {
        ArrayList<String> targets = new ArrayList<>();

        for (Place place : possibleTargets) {
            targets.add(Place.placeAsString(place));
        }

        return targets;
    }

    void setInBase() {
        this.currentPlace = Place.BASE;
        this.distanceToDestiny = 0;
        this.transportStatus = TransportStatus.WAITING;
    }

    public abstract ConsumablesPack costOfFix();
    public abstract ConsumablesPack requiredResourcesToStart();
}
