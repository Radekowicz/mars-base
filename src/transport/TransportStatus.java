package transport;

public enum TransportStatus {
    WAITING,
    ON_THE_WAY,
    LOADED,
    UNLOADED,
    DAMAGED,
    DESTROYED,
    ORDERED;

    /**
     * Return transport status as string
     * @param transportStatus transport status
     * @return transport status as string
     */
    public static String transportStatusAsString(TransportStatus transportStatus) {
        switch (transportStatus) {
            case WAITING:
                return "waiting";
            case ON_THE_WAY:
                return "waiting";
            case LOADED:
                return "waiting";
            case UNLOADED:
                return "waiting";
            case DAMAGED:
                return "waiting";
            case DESTROYED:
                return "waiting";
            case ORDERED:
                return "waiting";
        }

        return "";
    }
}
