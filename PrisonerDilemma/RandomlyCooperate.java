package PrisonerDilemma;

import mvc.Utilities;

public class RandomlyCooperate extends Strategy {

    public RandomlyCooperate(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean cooperate() {
        return Utilities.rng.nextBoolean();
    }
}

