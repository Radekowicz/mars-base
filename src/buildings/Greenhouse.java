package buildings;

import resources.ResourcePack;

public class Greenhouse extends Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(0, 0, 100);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(30, 0, 0);
    }

    @Override
    public  ResourcePack costOfBuilding() { return new ResourcePack(200, 100, 0); }

    @Override
    public int timeOfBuild() { return 5; }

    @Override
    public String getName() {
        return " Greenhouse";
}
}
