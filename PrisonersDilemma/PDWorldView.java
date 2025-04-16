package PrisonersDilemma;

import SimStation.WorldView;
import mvc.Model;
import java.awt.Graphics;

public class PDWorldView extends WorldView {
    public PDWorldView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String stats = ((PDModel)getModel()).getStatistics();
        g.drawString(stats, 10, 20);
    }
}
