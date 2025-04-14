package SimStation;

public class SimStationMain {
    public static void main(String[] args) {
        SimStationFactory factory = new SimStationFactory();
        WorldPanel panel = new WorldPanel(factory);
        panel.display();
    }
}
