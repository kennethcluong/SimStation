package PrisonerDilemma;

public class Cooperate extends Strategy {

    public Cooperate(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean cooperate() {
        return true;
    }
}

