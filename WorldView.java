package SimStation;

import mvc.View;
import mvc.Model;
import java.awt.Graphics;
import java.awt.Color;

public class WorldView extends View {
    public WorldView(Model model) {
        super(model);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Ensure the model is a World instance.
        if (model instanceof World) {
            World w = (World) model;
            for (Agent a : w.agents) {
                drawAgent(a, g);
            }
        }
    }

    // Draws an agent as a red circle.
    public void drawAgent(Agent a, Graphics g) {
        g.setColor(Color.RED);
        int diameter = 10;
        g.fillOval(a.getX(), a.getY(), diameter, diameter);
    }
}
