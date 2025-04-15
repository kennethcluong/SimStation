package SimStation.Greed;

import SimStation.World;
import SimStation.ObserverAgent;
import java.util.Random;

public class Meadow extends World {
    // These static fields are set by sliders in GreedPanel:
    public static int GROWBACK_RATE = 1;
    public static int WAIT_PENALTY   = 1;
    public static int MAX_PATCH_ENERGY = 100;
    public static int NUM_COWS       = 20;
    public static int PATCH_SIZE     = 10;

    private Patch[][] patches;
    private int dim;

    public Meadow() {
        super();
        dim = SIZE / PATCH_SIZE;
    }

    @Override
    public void populate() {
        patches = new Patch[dim][dim];
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                int x = col * PATCH_SIZE;
                int y = row * PATCH_SIZE;
                Patch p = new Patch("Patch(" + row + "," + col + ")", x, y, this);
                patches[row][col] = p;
                addAgent(p);
            }
        }
        Random rng = new Random();
        for (int i = 0; i < NUM_COWS; i++) {
            int x = rng.nextInt(SIZE);
            int y = rng.nextInt(SIZE);
            Cow c = new Cow("Cow" + i, x, y, this);
            addAgent(c);
        }
        addAgent(new ObserverAgent(this));
    }

    public Patch getPatch(int x, int y) {
        int row = Math.max(0, Math.min(dim - 1, y / PATCH_SIZE));
        int col = Math.max(0, Math.min(dim - 1, x / PATCH_SIZE));
        return patches[row][col];
    }

    public Patch[][] getPatches() {
        return patches;
    }
}
