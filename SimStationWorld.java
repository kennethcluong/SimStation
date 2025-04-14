package SimStation;

public class SimStationWorld extends World {
    public SimStationWorld() {
        // Optionally, add an observer to update statistics automatically.
        addAgent(new ObserverAgent(this));
    }

    public void populate() {
        // Create a set number of simple agents.
        for (int i = 0; i < 10; i++) {
            addAgent(new SimpleAgent("Agent" + i, (int)(Math.random() * SIZE), (int)(Math.random() * SIZE)));
        }
    }
}
