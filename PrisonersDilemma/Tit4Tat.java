package PrisonersDilemma;

public class Tit4Tat extends Strategy {

    public Tit4Tat(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean cooperate() {
        return !owner.wasPartnerCheatedLastTime();
    }
}


