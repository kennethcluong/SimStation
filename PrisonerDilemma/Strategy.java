package PrisonerDilemma;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    protected Prisoner owner;

    public Strategy(Prisoner owner) {
        this.owner = owner;
    }

    public abstract boolean cooperate();
}

