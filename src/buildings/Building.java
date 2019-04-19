package buildings;

import resources.ResourcePack;

import java.util.List;

public interface Building {

    ResourcePack generateResources();
    ResourcePack consumeResources();

    String getName();

}
