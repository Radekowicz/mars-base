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
 *
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

    public void unload(EventListener eventListener) {
        System.out.println("DUPA8 " + currentCP.toString());
        eventListener.eventOccurred(this);
        ResourcesManager.add(currentCP, currentUP);
        this.currentCP.zeroing();
        this.currentUP.zeroing();
        this.transportStatus = TransportStatus.WAITING;
        this.currentPlace = target;
        returning = false;
    }

    public String getName() {
        return this.name;
    }

    public Place getTarget() {
        return target;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public boolean targetIsPossible(Place target) {
        return possibleTargets.contains(target);
    }

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
