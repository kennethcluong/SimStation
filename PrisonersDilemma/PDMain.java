package PrisonersDilemma;

import mvc.AppFactory;
import javax.swing.SwingUtilities;
import SimStation.WorldPanel;

public class PDMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFactory factory = new PDFactory();
            WorldPanel  panel   = new WorldPanel(factory);
            panel.display();
        });
    }
}
