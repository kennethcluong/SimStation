package SimStation.Greed;

import SimStation.MobileAgent;
import SimStation.Heading;

public class Cow extends MobileAgent {
    // These static fields are set by sliders in GreedPanel:
    public static int GREEDINESS = 25;
    public static int MOVE_ENERGY = 10;

    private Meadow meadow;
    private int energy;

    public Cow(String name, int x, int y, Meadow meadow) {
        super(name, x, y, Heading.random());
        this.meadow = meadow;
        this.energy = 100;
        // use the static fields so each cow "reads" current slider values
        // or store them in instance fields, e.g.: this.greediness = GREEDINESS;
    }

    public int getEnergy() { return energy; }
    public void addEnergy(int amt)  { energy = Math.min(100, energy + amt); }
    public void decrementEnergy(int amt) { energy = Math.max(0, energy - amt); }

    @Override
    protected void update() {
        if (energy <= 0) { stopAgent(); return; }
        Patch patch = meadow.getPatch(x, y);

        // "Cow.GREEDINESS" so that it always reflects the current slider value:
        int demand = Cow.GREEDINESS;
        if (patch.getEnergy() >= demand) {
            patch.eatMe(this, demand);
        } else {
            if (energy >= MOVE_ENERGY) {
                decrementEnergy(MOVE_ENERGY);
                turn(Heading.random());
                move(Meadow.PATCH_SIZE);
            } else {
                patch.eatMe(this, demand);
            }
        }
        // Minor upkeep cost
        decrementEnergy(1);
        if (energy <= 0) stopAgent();
    }
}
