package simStation;

import mvc.Utilities;

public abstract class Agent implements Runnable, java.io.Serializable {
    protected int x, y;                     // Agent's current coordinates
    protected String agentName;             // Name identifier
    transient protected Thread myThread;    // Transient because threads are not serializable
    protected World world;
    protected boolean suspended;            // Flag to suspend execution
    protected boolean stopped;              // Flag to stop execution

    public Agent(String name, int x, int y) {
        this.agentName = name;
        this.x = x;
        this.y = y;
        this.suspended = false;
        this.stopped = false;
    }

    public String getAgentName() { return agentName; }
    public int getX() { return x; }
    public int getY() { return y; }

    // Start the agent’s thread
    public void start() {
        if (myThread == null) {
            myThread = new Thread(this);
            myThread.start();
        }
    }

    // Stop the agent
    public void stopAgent() {
        stopped = true;
    }

    public void setManager(World w)
    {
        this.world = w;
    }

    // Suspend the agent's execution
    public void suspendAgent() {
        suspended = true;
    }

    // Resume the agent's execution
    public void resumeAgent() {
        suspended = false;
        synchronized(this) {
            notify();  // wake up the waiting thread
        }
    }

    public void run() {
        while(!stopped) {
            try {
                // Wait if suspended
                synchronized(this) {
                    while (suspended)
                        wait();
                }
                update(); // Execute agent-specific behavior
                Thread.sleep(20); // Sleep 20 msec for smooth animation
            } catch (InterruptedException e) {
                Utilities.error(e);
            }
            world.changed();
        }
    }

    // Abstract method: each agent type defines its own behavior
    protected abstract void update();
}