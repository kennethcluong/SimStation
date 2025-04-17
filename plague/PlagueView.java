package simStation.plague;

import mvc.*;
import simStation.*;
import java.awt.*;

public class PlagueView extends WorldView {
    public PlagueView(Model model) {
        super(model);
    }

    @Override
    public void drawAgent(Agent a, Graphics g) {
        if (a instanceof Plague) {
            if (((Plague) a).isDead) {
                g.setColor(Color.BLACK);
            }
            else if (((Plague) a).isInfected()) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.GREEN);
            }
            g.fillOval(a.getX(), a.getY(), 10, 10);
        }
        else {
            super.drawAgent(a, g);
        }
    }

    public void update() {
        PlagueSimulation model = (PlagueSimulation) this.getModel();
        repaint();
    }
}
