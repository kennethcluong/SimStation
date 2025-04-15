package PrisonerDilemma;

import mvc.View;
import java.awt.Graphics;

public class PDView extends View {

    public PDView(PDModel model) {
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
