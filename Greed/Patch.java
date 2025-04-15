package SimStation.Greed;

import SimStation.Agent;

public class Patch extends Agent {
    private Meadow meadow;
    private int energy;

    public Patch(String name, int x, int y, Meadow meadow) {
        super(name, x, y);
        this.meadow = meadow;
        this.energy = 50; // Start with half of maximum energy.
    }

    // Returns current energy.
    public synchronized int getEnergy() {
        return energy;
    }

    // A cow calls this to eat a specified amount (demand).
    // If there isn’t enough energy, the cow waits and loses energy from waiting.
    public synchronized void eatMe(Cow cow, int demand) {
        while (energy < demand) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cow.decrementEnergy(Meadow.WAIT_PENALTY);
            if (cow.getEnergy() <= 0) {
                cow.stopAgent();
                return;
            }
        }
        energy -= demand;
        cow.addEnergy(demand);
        notifyAll();  // Notify waiting cows to re-check the energy
    }

    @Override
    protected void update() {
        // Regrow grass up to maximum capacity.
        energy = Math.min(energy + Meadow.GROWBACK_RATE, Meadow.MAX_PATCH_ENERGY);
        notifyAll();  // Let any waiting cows know that energy might have increased.
    }
}
