package SimStation;

public class ObserverAgent extends Agent {
    private World world;

    public ObserverAgent(World world) {
        super("Observer", 0, 0);
        this.world = world;
    }
    protected void update() {
        world.updateStatistics();
        // Sleep longer since statistics update less frequently.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Handle exception if needed.
        }
    }
}
