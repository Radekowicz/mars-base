package events;

import java.util.Random;

public final class EventManager {
    private static EventManager eventManager = null;

    private static int chanceForEvent;
    private static int originalTimeBreak;
    private static int currentTimeBreak;
    private static int n;

    public EventManager(int chanceForEvent, int originalTimeBreak) {
        this.chanceForEvent = chanceForEvent;
        this.originalTimeBreak = originalTimeBreak;
        currentTimeBreak = originalTimeBreak;
        n = 1;
    }

    public static void initializeEventManager(int chanceForEvent, int originalTimeBreak) {
        if (eventManager == null)
            eventManager = new EventManager(chanceForEvent, originalTimeBreak);
    }

    public static void tryTriggerEvent() {
        if (currentTimeBreak != 0) {
            currentTimeBreak--;
        }
        else {
            Random random = new Random();
            if (random.nextInt(101) < chanceForEvent) {
                Event event = getEvent();
                event.execute();
                currentTimeBreak = originalTimeBreak;
            }
            else{
                currentTimeBreak = originalTimeBreak / (int)Math.pow(2, n);
                n++;
            }
        }
    }

    private static Event getEvent() {
        Random random = new Random();
        int choose = random.nextInt(101);

        if (choose < 20)
            return new MeteorShower();
        else
            return new Sandstorm();
    }
}
