package PrisonersDilemma;

import mvc.Utilities;
import mvc.Model;
import java.util.ArrayList;
import java.util.List;

public class PDModel extends Model {
    private List<Prisoner> prisoners = new ArrayList<>();

    public PDModel() {
        super();
        // Create 40 prisoners: 10 per strategy.
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner();
            p.setStrategy(new Cheat(p));
            prisoners.add(p);
        }
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner();
            p.setStrategy(new Cooperate(p));
            prisoners.add(p);
        }
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner();
            p.setStrategy(new RandomlyCooperate(p));
            prisoners.add(p);
        }
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner();
            p.setStrategy(new Tit4Tat(p));
            prisoners.add(p);
        }
    }

    public void step() {
        if (prisoners.size() < 2) return;

        // For each prisoner, play round with randomly selected opponent.
        for (Prisoner p : prisoners) {
            Prisoner opponent = getRandomPrisoner(p);
            playRound(p, opponent);
        }
        // Notify subscribers that model changed.
        changed();
    }

    /**
     * Play one round between two prisoners using payoff matrix:
     * - Both cooperate: +3 points each.
     * - One cooperates and other cheats: cheater gets +5, cooperator gets 0.
     * - Both cheat: +1 point each.
     */
    private void playRound(Prisoner p, Prisoner q) {
        boolean pMove = p.cooperate();
        boolean qMove = q.cooperate();

        if (pMove && qMove) {
            p.addFitness(3);
            q.addFitness(3);
            p.setPartnerCheatedLastTime(false);
            q.setPartnerCheatedLastTime(false);
        } else if (pMove && !qMove) {
            q.addFitness(5);
            p.setPartnerCheatedLastTime(true);
            q.setPartnerCheatedLastTime(false);
        } else if (!pMove && qMove) {
            p.addFitness(5);
            p.setPartnerCheatedLastTime(false);
            q.setPartnerCheatedLastTime(true);
        } else {
            p.addFitness(1);
            q.addFitness(1);
            p.setPartnerCheatedLastTime(true);
            q.setPartnerCheatedLastTime(true);
        }
    }

    private Prisoner getRandomPrisoner(Prisoner me) {
        Prisoner result = me;
        while (result == me) {
            int i = Utilities.rng.nextInt(prisoners.size());
            result = prisoners.get(i);
        }
        return result;
    }

    public String getStatistics() {
        double sumCheat = 0, nCheat = 0;
        double sumCoop = 0, nCoop = 0;
        double sumRand = 0, nRand = 0;
        double sumT4T = 0, nT4T = 0;

        for (Prisoner p : prisoners) {
            int f = p.getFitness();
            Strategy s = p.getStrategy();
            if (s instanceof Cheat) {
                sumCheat += f;
                nCheat++;
            } else if (s instanceof Cooperate) {
                sumCoop += f;
                nCoop++;
            } else if (s instanceof RandomlyCooperate) {
                sumRand += f;
                nRand++;
            } else if (s instanceof Tit4Tat) {
                sumT4T += f;
                nT4T++;
            }
        }
        return "Cheater avg: " + (sumCheat / (nCheat == 0 ? 1 : nCheat)) +
                "   Cooperator avg: " + (sumCoop / (nCoop == 0 ? 1 : nCoop)) +
                "   Random avg: " + (sumRand / (nRand == 0 ? 1 : nRand)) +
                "   Tit4Tat avg: " + (sumT4T / (nT4T == 0 ? 1 : nT4T));
    }

    public List<Prisoner> getPrisoners() {
        return prisoners;
    }
}
