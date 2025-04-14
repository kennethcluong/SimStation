package SimStation;

import mvc.Utilities;

public abstract class MobileAgent extends Agent {
    protected Heading heading;  // Agent's direction

    public MobileAgent(String name, int x, int y, Heading heading) {
        super(name, x, y);
        this.heading = heading;
    }

    public Heading getHeading() { return heading; }

    // Moves the agent a certain number of steps in its current heading.
    public void move(int steps) {
        switch(heading) {
            case NORTH:
                y -= steps;
                break;
            case SOUTH:
                y += steps;
                break;
            case EAST:
                x += steps;
                break;
            case WEST:
                x -= steps;
                break;
        }
        // Wrap-around: if the agent goes outside of the world, wrap it back in.
        int worldSize = SimStationWorld.SIZE;
        if (x < 0) x += worldSize;
        if (x >= worldSize) x = x % worldSize;
        if (y < 0) y += worldSize;
        if (y >= worldSize) y = y % worldSize;
    }

    // Changes the agent's heading.
    public void turn(Heading newHeading) {
        this.heading = newHeading;
    }
}
