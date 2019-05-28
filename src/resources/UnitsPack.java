package resources;

public final class UnitsPack {
    private long humans;
    private long robots;

    public UnitsPack(long humans, long robots) {
        this.humans = humans;
        this.robots = robots;
    }

    public long getHumans() {
        return humans; }

    public long getRobots() {
        return robots; }

    public void addHumans(long h) { this.humans += h; }

    public void addRobots(long r) {
        this.robots += r;
    }

    public void subtractHumans(long h) {
        this.humans -= h;
    }

    public void subtractRobots(long r) {
        this.robots -= r;
    }

    public void subtract(UnitsPack otherUp){
        if(isEnough(otherUp)){
            this.humans -= otherUp.humans;
            this.robots -= otherUp.robots;
        }
    }
    public boolean isEnough(UnitsPack uP){
        return this.humans >= uP.getHumans() && this.robots >= uP.robots;
    }

    public void zeroing() {
        this.humans = 0;
        this.robots = 0;
    }

    @Override
    public String toString() {
        return "Units Pack: " +
                "Human {" + this.humans + "}" +
                "Robot {" + this.robots + "}";
    }
}
