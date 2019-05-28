package resources;

public final class UnitsPack {
    private long humans;
    private long robots;

    /**
     * Sets resources
     * @param humans represents amount of humans
     * @param robots represents amount of robots
     */
    public UnitsPack(long humans, long robots) {
        this.humans = humans;
        this.robots = robots;
    }

    /**
     * @return returs current amount of humans
     */
    public long getHumans() {
        return humans; }
    /**
     * @return returs current amount of robots
     */
    public long getRobots() {
        return robots; }

    /**
     * adds particular amount of humans
     * @param h amount of humans which will be added
     */
    public void addHumans(long h) { this.humans += h; }

    /**
     * adds particular amount of robots
     * @param r amount of robots which will be added
     */
    public void addRobots(long r) {
        this.robots += r;
    }

    /**
     * subtracts particular amount of humans
     * @param h amount of humans which will be subtracted
     */
    public void subtractHumans(long h) {
        this.humans -= h;
    }

    /**
     * subtracts particular amount of robots
     * @param r amount of robots which will be subtracted
     */
    public void subtractRobots(long r) {
        this.robots -= r;
    }

    /**
     * subtracts particular amount of robots and humans
     * @param otherUp UnitsPack variable containing amount of humans and robots which will be subtracted
     */
    public void subtract(UnitsPack otherUp){
        if(isEnough(otherUp)){
            this.humans -= otherUp.humans;
            this.robots -= otherUp.robots;
        }
    }

    /**
     *  checks if there is more robots and humans then in param
     * @param uP amount of robots and humans
     * @return return false if there is more robots or humans in param
     */
    public boolean isEnough(UnitsPack uP){
        return this.humans >= uP.getHumans() && this.robots >= uP.robots;
    }

    /**
     * sets amount of humans and robots to zero
     */
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
