package buildings;



import resources.ResourcePack;

import java.util.ArrayList;
import java.util.List;

public class PowerStation implements Building {

    @Override
    public ResourcePack generateResources() {
        return new ResourcePack(10, 0, 0);
    }

    @Override
    public ResourcePack consumeResources() {
        return new ResourcePack(0, 0, 5);
    }

    @Override
    public String getName() {
        return " Power Station";
    }
}
