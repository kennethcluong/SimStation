package SimStation.Greed;

import SimStation.WorldPanel;
import mvc.AppFactory;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GreedPanel extends WorldPanel {

    public GreedPanel(AppFactory factory) {
        super(factory);

        // By default, WorldPanel uses a GridLayout(1, 2),
        // so let’s override that so we can explicitly put
        // our pink panel to the WEST and the simulation
        // view (already in 'view') in the CENTER.
        setLayout(new BorderLayout());

        // the pink panel: place it in the WEST
        JPanel pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);
        pinkPanel.setLayout(new BorderLayout());

        // Move your "threadPanel" (the row of Start/Pause/Resume/Stop/Stats buttons)
        // to the top (NORTH) of pinkPanel:
        pinkPanel.add(threadPanel, BorderLayout.NORTH);

        // Now create a separate panel to hold the sliders in the CENTER of pinkPanel:
        JPanel sliderPanel = new JPanel();
        sliderPanel.setOpaque(false); // so the pink background shows through
        sliderPanel.setLayout(new GridLayout(3, 1, 10, 10));
        // 3 rows for (1) Greed, (2) Grow-back, (3) Move energy

        // 1) GREED Slider
        JPanel row1 = new JPanel(new BorderLayout());
        row1.setOpaque(false);
        JLabel greedLabel = new JLabel("Greed:");
        JSlider greedSlider = new JSlider(0, 100, Cow.GREEDINESS);
        greedSlider.setMajorTickSpacing(20);
        greedSlider.setPaintTicks(true);
        greedSlider.setPaintLabels(true);
        greedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Cow.GREEDINESS = greedSlider.getValue();
            }
        });
        row1.add(greedLabel, BorderLayout.NORTH);
        row1.add(greedSlider, BorderLayout.CENTER);
        sliderPanel.add(row1);

        // 2) GROW BACK RATE Slider
        JPanel row2 = new JPanel(new BorderLayout());
        row2.setOpaque(false);
        JLabel growLabel = new JLabel("Grow back rate:");
        JSlider growSlider = new JSlider(0, 10, Meadow.GROWBACK_RATE);
        growSlider.setMajorTickSpacing(2);
        growSlider.setPaintTicks(true);
        growSlider.setPaintLabels(true);
        growSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Meadow.GROWBACK_RATE = growSlider.getValue();
            }
        });
        row2.add(growLabel, BorderLayout.NORTH);
        row2.add(growSlider, BorderLayout.CENTER);
        sliderPanel.add(row2);

        // 3) MOVE ENERGY Slider
        JPanel row3 = new JPanel(new BorderLayout());
        row3.setOpaque(false);
        JLabel moveLabel = new JLabel("Move Energy:");
        JSlider moveSlider = new JSlider(0, 50, Cow.MOVE_ENERGY);
        moveSlider.setMajorTickSpacing(10);
        moveSlider.setPaintTicks(true);
        moveSlider.setPaintLabels(true);
        moveSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Cow.MOVE_ENERGY = moveSlider.getValue();
            }
        });
        row3.add(moveLabel, BorderLayout.NORTH);
        row3.add(moveSlider, BorderLayout.CENTER);
        sliderPanel.add(row3);

        pinkPanel.add(sliderPanel, BorderLayout.CENTER);

        // Now add pinkPanel on the WEST, and your simulation view on the CENTER
        add(pinkPanel, BorderLayout.WEST);
        add(view, BorderLayout.CENTER);

        // We remove the old 'controlPanel.add(...)' calls from the original WorldPanel
        // because we’re handling layout here directly.
    }
}
