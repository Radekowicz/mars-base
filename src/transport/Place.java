package transport;

/**
 * Represents place in space and on the Mars's surface
 */
public enum Place {
    EARTH,
    ASTEROID,
    PLANETOID,
    SPACE,
    MARS,
    BASE;

    public static String placeAsString(Place place) {
        switch (place) {
            case EARTH:
                return "Earth";
            case ASTEROID:
                return "Asteroid";
            case PLANETOID:
                return "Planetoid";
            case SPACE:
                return "Space";
            case MARS:
                return "Mars";
            case BASE:
                return "Base";
        }

        return "";
    }

    public static Place getPlaceFromString(String place) {
        place = place.toLowerCase();

        switch (place) {
            case "earth":
                return EARTH;
            case "mars":
                return MARS;
            case "asteroid":
                return ASTEROID;
            case "planetoid":
                return PLANETOID;
            case "space":
                return SPACE;
            case "base":
                return BASE;
            default:
                return BASE;
        }
    }
}
