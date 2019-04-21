package buildings;

import resources.ResourcePack;

public class Hub extends Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(0,0,0);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(20, 10, 30);
    }

    @Override
    public int timeOfBuild() {
        return 10;
    }

    @Override
    public String getName() {
        return " Hub";
    }
}
