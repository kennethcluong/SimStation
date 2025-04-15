package SimStation;

public class SimpleAgent extends MobileAgent {
    public SimpleAgent(String name, int x, int y) {
        // Start with a random heading.
        super(name, x, y, Heading.random());
    }

    protected void update() {
        // Move a fixed number of steps in the current direction.
        move(5);
        // Occasionally change direction (10% chance on each update).
        if (Math.random() < 0.1) {
            turn(Heading.random());
        }
    }
}
