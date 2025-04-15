package SimStation.Greed;

import mvc.AppFactory;

public class GreedMain {
    public static void main(String[] args) {
        AppFactory factory = new GreedFactory();
        // Use the custom panel with sliders & pink layout
        GreedPanel panel = new GreedPanel(factory);
        panel.display();
    }
}
