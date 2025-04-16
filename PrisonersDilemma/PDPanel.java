package PrisonersDilemma;

import mvc.Utilities;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDPanel extends JPanel {

    private JButton stepButton;
    private JSlider speedSlider;
    private PDModel model;

    public PDPanel(PDModel model) {
        this.model = model;

        stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.step();
            }
        });
        add(stepButton);

        speedSlider = new JSlider(1, 10, 5);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = speedSlider.getValue();
                Utilities.log("Slider changed to: " + val);
            }
        });
        add(speedSlider);
    }
}
