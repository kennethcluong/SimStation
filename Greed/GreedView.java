package SimStation.Greed;

import SimStation.WorldView;
import mvc.Model;
import java.awt.*;

public class GreedView extends WorldView {

    public GreedView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(model instanceof Meadow meadow)) {
            return;
        }

        // fill background grey (optional)
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        Patch[][] grid = meadow.getPatches();
        if (grid == null) return;

        int patchSize = Meadow.PATCH_SIZE;
        // draw patches in green scale
        for (Patch[] row : grid) {
            for (Patch p : row) {
                int e = p.getEnergy(); // 0..100
                int greenVal = (int)(e / 100.0 * 255);
                // from black (0,0,0) up to bright green (0,255,0)
                g.setColor(new Color(0, greenVal, 0));
                g.fillRect(p.getX(), p.getY(), patchSize, patchSize);
            }
        }

        // draw cows in red squares on top
        meadow.iterator().forEachRemaining(a -> {
            if (a instanceof Cow cow) {
                g.setColor(Color.RED);
                g.fillRect(cow.getX(), cow.getY(), patchSize, patchSize);
            }
        });
    }
}
