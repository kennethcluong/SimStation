package PrisonerDilemma;

public class Cheat extends Strategy {

    public Cheat(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean cooperate() {
        return false;
    }
}
