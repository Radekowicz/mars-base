package buildings;

import resources.ResourcePack;

import java.util.ArrayList;
import java.util.List;

public class Hub implements Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(0,0,0);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(20, 10, 30);
    }

    @Override
    public String getName() {
        return " Hub";
    }
}
