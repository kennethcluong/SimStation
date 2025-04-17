package simStation;

import mvc.Model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class World extends Model {
    public static final int SIZE = 400;  // Define world dimensions

    protected int clock = 0;
    protected int alive = 0;
    protected ArrayList<Agent> agents;

    public World() {

        agents = new ArrayList<>();
    }

    // Abstract method used to create/populate the simulation.
    public abstract void populate();

    // Add an agent to the world.
    public void addAgent(Agent a) {
        a.setManager(this);
        agents.add(a);
    }

    // Returns an iterator for all agents.
    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    // Start all agents in the simulation.
    public void startAgents() {
        populate();  // Create/populate agents first
        for (Agent a : agents) {
            a.start();
        }
    }

    // Stop all agents.
    public void stopAgents() {
        for (Agent a : agents) {
            a.stopAgent();
        }
    }

    // Pause all agents.
    public void pauseAgents() {
        for (Agent a : agents) {
            a.suspendAgent();
        }
    }

    // Resume all agents.
    public void resumeAgents() {
        for (Agent a : agents) {
            a.resumeAgent();
        }
    }

    // Update simulation statistics.
    public void updateStatistics() {
        clock++;
        alive = agents.size(); // In this simple example, assume all agents are "alive."
        changed();  // Notify subscribers (views) that the model has changed.
    }

    // Finds a neighbor agent (other than the caller) within a given "radius" (in steps).
    public Agent getNeighbor(Agent caller, int radius) {
        if (agents.isEmpty()) return null;
        Random rng = new Random();
        int startIndex = rng.nextInt(agents.size());
        int index = startIndex;
        do {
            Agent candidate = agents.get(index);
            if (candidate != caller) {
                int dx = Math.abs(candidate.getX() - caller.getX());
                int dy = Math.abs(candidate.getY() - caller.getY());
                if (dx <= radius && dy <= radius) {
                    return candidate;
                }
            }
            index = (index + 1) % agents.size();
        } while (index != startIndex);
        return null;
    }

    public int getClock() { return clock; }
    public int getAlive() { return alive; }
}
