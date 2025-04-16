package PrisonersDilemma;

import SimStation.MobileAgent;
import SimStation.Heading;
import SimStation.World;

public class Prisoner extends MobileAgent {
    private int fitness = 0;
    private boolean partnerCheatedLastTime = false;
    private Strategy strategy;
    private World world;

    public Prisoner(World world, String name, int x, int y) {
        super(name, x, y, Heading.random());
        this.world = world;
    }

    public void setStrategy(Strategy strategy) { this.strategy = strategy; }
    public Strategy getStrategy()            { return strategy; }
    public int getFitness()                  { return fitness; }
    public boolean wasPartnerCheatedLastTime() { return partnerCheatedLastTime; }
    public void setPartnerCheatedLastTime(boolean cheated) { partnerCheatedLastTime = cheated; }
    public boolean cooperate()               { return strategy.cooperate(); }

    @Override
    protected void update() {
        SimStation.Agent raw = world.getNeighbor(this, World.SIZE);
        if (!(raw instanceof Prisoner)) return;
        Prisoner neighbor = (Prisoner) raw;

        boolean me   = strategy.cooperate();
        boolean them = neighbor.strategy.cooperate();

        if (me && them) {
            fitness += 3; neighbor.fitness += 3;
            partnerCheatedLastTime = false; neighbor.partnerCheatedLastTime = false;
        } else if (me && !them) {
            neighbor.fitness += 5;
            partnerCheatedLastTime = true;  neighbor.partnerCheatedLastTime = false;
        } else if (!me && them) {
            fitness += 5;
            partnerCheatedLastTime = false; neighbor.partnerCheatedLastTime = true;
        } else {
            fitness += 1; neighbor.fitness += 1;
            partnerCheatedLastTime = true;  neighbor.partnerCheatedLastTime = true;
        }
    }
}
