package transport;

import resources.ConsumablesPack;
import resources.UnitsPack;

public abstract class Transport {
    private int speed;
    private int fuelConsumption;
    private ConsumablesPack maxConsumableResourcesCapacity ;
    private ConsumablesPack currentConsumableResourcesCapacity ;
    private UnitsPack maxUnitsCapacity;
    private UnitsPack currentUnitsCapacity;
    private TransportStatus transportStatus = TransportStatus.WAITNIG;
    private String name;
}
