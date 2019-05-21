package transport;

import events.Destructible;
import events.Fixable;
import resources.ConsumablesPack;
import resources.ResourcesManager;
import resources.UnitsPack;

import java.util.List;
import java.util.Random;

public abstract class Transport implements Destructible, Fixable {
    private int speed;
    private String name;
    private ConsumablesPack maxCPCapacity;
    private UnitsPack maxUPCapacity;
    private ConsumablesPack currentCP;
    private UnitsPack currentUP;
    private TransportStatus transportStatus;

    private List<Target> possibleTargets;

    public Transport(int speed, String name, ConsumablesPack maxCPCapacity, UnitsPack maxUPCapacity) {
        this.speed = speed;
        this.name = name;
        this.maxCPCapacity = maxCPCapacity;
        this.maxUPCapacity = maxUPCapacity;
        this.currentCP = new ConsumablesPack(0,0,0,0,0,0);
        this.currentUP = new UnitsPack(0,0);
        this.transportStatus = TransportStatus.ON_THE_WAY;
    }

    public String getName() {
        return this.name;
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
    }

    public void unload() {
        ResourcesManager.add(currentCP, currentUP);
        this.currentCP.zeroing();
        this.currentUP.zeroing();
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

    public abstract ConsumablesPack costOfFix();

    protected void setPossibleTargets(List<Target> possibleTargets) {
        this.possibleTargets = possibleTargets;
    }
}
