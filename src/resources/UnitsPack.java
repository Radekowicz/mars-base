package resources;

public class UnitsPack {
    private long humans;
    private long robots;

    public UnitsPack(long humans, long robots) {
        this.humans = humans;
        this.robots = robots;
    }

    public long getHumans() { return humans; }
    public void setHumans(long humans) { this.humans = humans; }

    public long getRobots() { return robots; }
    public void setRobots(long robots) { this.robots = robots; }

    public void addHumans(long h) { this.humans += h;}
    public void addRobots(long r) {this.robots += r; }

    public void subtractHumans(long h) { this.humans -= h;}
    public void subtractRobots(long r) {this.robots -= r; }

    public boolean isEnough(UnitsPack uP){
        return this.humans >= uP.getHumans() && this.robots >= uP.robots;
    }
}
