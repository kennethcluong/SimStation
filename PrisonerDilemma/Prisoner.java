package PrisonersDilemma;

import java.io.Serializable;

public class Prisoner implements Serializable {

    private int fitness = 0;
    private boolean partnerCheatedLastTime = false;
    private Strategy strategy;

    // No-argument constructor.
    public Prisoner() {}

    // Setter for strategy.
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public int getFitness() {
        return fitness;
    }

    public void addFitness(int amt) {
        fitness += amt;
    }

    public boolean wasPartnerCheatedLastTime() {
        return partnerCheatedLastTime;
    }

    public void setPartnerCheatedLastTime(boolean cheated) {
        partnerCheatedLastTime = cheated;
    }

    //Goes to strategy to decide whether to cooperate.
    public boolean cooperate() {
        return strategy.cooperate();
    }
}
