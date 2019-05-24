package transport;

import events.DescriptionPublisher;
import events.Destructible;
import events.EventListener;
import events.Fixable;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.List;
import java.util.Random;

/**
 *
 */
public abstract class Transport implements Destructible, Fixable, DescriptionPublisher {
    private int speed;
    private String name;
    private ConsumablesPack maxCPCapacity;
    private UnitsPack maxUPCapacity;
    private ConsumablesPack currentCP;
    private UnitsPack currentUP;
    private TransportStatus transportStatus;
    private Place currentPlace;
    private Place target;
    private int distanceToDestiny;
    private List<Place> possibleTargets;

    public Transport(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity, List<Place> possibleTargets) {
        this.speed = speed;
        this.name = name;
        this.maxCPCapacity = maxCPCapacity;
        this.maxUPCapacity = maxUPCapacity;
        this.currentCP = new ConsumablesPack(0,0,0,0,0,0);
        this.currentUP = new UnitsPack(0,0);
        this.possibleTargets = possibleTargets;
        this.transportStatus = TransportStatus.ON_THE_WAY;
        this.currentPlace = Place.SPACE;
        this.target = Place.MARS;
    }

    public String getName() {
        return this.name;
    }

    public Place getTarget() {
        return target;
    }

    public void setTarget(Place target) {
        if (target == null)
            this.target = target;
    }

    public Place getCurrentPlace() {
        return currentPlace;
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
    }


    public void unload(EventListener eventListener) {
        eventListener.eventOccurred(this);
        ResourcesManager.add(currentCP, currentUP);
        this.currentCP.zeroing();
        this.currentUP.zeroing();
        this.transportStatus = TransportStatus.WAITING;
        this.currentPlace = Place.MARS;
    }

    @Override
    public void damage() {
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
        String status = "";

        switch (transportStatus) {
            case WAITING:
                status = "waiting";
                break;
            case DAMAGED:
                status = "damaged";
                break;
            case LOADED:
                status = "loading";
                break;
            case UNLOADED:
                status = "unloading";
                break;
            case ON_THE_WAY:
                status = "on the way";
            default:
                break;
        }

        return status;
    }

    public void move() {
        if (transportStatus != TransportStatus.ON_THE_WAY)
            return;

        if ((distanceToDestiny - speed) <= 0) {
            distanceToDestiny = 0;
            currentPlace = target;
            target = Place.MARS;
        }

        distanceToDestiny -= speed;
    }

    public boolean send(Place target, int distanceToDestiny) {
        if (distanceToDestiny <= 0)
            throw new IllegalArgumentException("distance must be positive");

        this.target = target;
        this.distanceToDestiny = distanceToDestiny;
        ResourcesManager.subtract(requiredResourcesToStart());
        return true;
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

    public abstract ConsumablesPack costOfFix();
    public abstract ConsumablesPack requiredResourcesToStart();
}
