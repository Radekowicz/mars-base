package buildings;

import resources.ResourcePack;

public class Greenhouse implements Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(0, 0, 100);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(30, 0, 0);
    }

    @Override
    public String getName() {
        return " Greenhouse";
}
}
