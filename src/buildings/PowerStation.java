package buildings;

import resources.ResourcePack;

public class PowerStation extends Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(10, 0, 0);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(0, 0, 5);
    }

    @Override
    public int timeOfBuild() { return 7; }

    @Override
    public String getName() {
        return " Power Station";
    }
}
