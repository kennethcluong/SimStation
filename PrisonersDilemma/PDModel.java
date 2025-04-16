package PrisonersDilemma;

import SimStation.World;
import SimStation.ObserverAgent;
import java.util.Random;

public class PDModel extends World {
    public PDModel() {
        super();
        addAgent(new ObserverAgent(this));
    }

    @Override
    public void populate() {
        Random rng = new Random();
        // 10 cheaters
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner(this, "Cheater" + i,
                    rng.nextInt(SIZE),
                    rng.nextInt(SIZE));
            p.setStrategy(new Cheat(p));
            addAgent(p);
        }
        // 10 cooperators
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner(this, "Cooperator" + i,
                    rng.nextInt(SIZE),
                    rng.nextInt(SIZE));
            p.setStrategy(new Cooperate(p));
            addAgent(p);
        }
        // 10 random
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner(this, "Random" + i,
                    rng.nextInt(SIZE),
                    rng.nextInt(SIZE));
            p.setStrategy(new RandomlyCooperate(p));
            addAgent(p);
        }
        // 10 tit‑for‑tat
        for (int i = 0; i < 10; i++) {
            Prisoner p = new Prisoner(this, "Tit4Tat" + i,
                    rng.nextInt(SIZE),
                    rng.nextInt(SIZE));
            p.setStrategy(new Tit4Tat(p));
            addAgent(p);
        }
    }

    public String getStatistics() {
        double sumC=0,nC=0, sumO=0,nO=0, sumR=0,nR=0, sumT=0,nT=0;
        for (Object o : agents) {
            if (!(o instanceof Prisoner)) continue;
            Prisoner p = (Prisoner)o;
            int f = p.getFitness();
            if (p.getStrategy() instanceof Cheat)            { sumC += f; nC++; }
            else if (p.getStrategy() instanceof Cooperate)   { sumO += f; nO++; }
            else if (p.getStrategy() instanceof RandomlyCooperate) { sumR += f; nR++; }
            else if (p.getStrategy() instanceof Tit4Tat)     { sumT += f; nT++; }
        }
        return "Cheat ⌀=" + (sumC/(nC==0?1:nC))
                + "   Coop ⌀=" + (sumO/(nO==0?nO:1))
                + "   Rand ⌀=" + (sumR/(nR==0?1:nR))
                + "   T4T ⌀="  + (sumT/(nT==0?1:nT));
    }
}
