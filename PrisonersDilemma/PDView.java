package PrisonersDilemma;

import mvc.View;
import mvc.Model;
import java.awt.Graphics;

public class PDView extends View {

    public PDView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        PDModel model = (PDModel) getModel();
        if (model != null) {
            String stats = model.getStatistics();
            gc.drawString(stats, 10, 20);
        }
    }
}
