package simStation.plague;

import mvc.*;
import simStation.Heading;
import simStation.MobileAgent;
import simStation.Agent;

import java.awt.*;

public class Plague extends MobileAgent {
    private boolean isInfected;
    private int timeInfected;
    private int fatalRecover;
    public boolean isDead;

    public Plague(int fatalRecover, String name, int x, int y) {
        super(name, x, y, Heading.random());
        isInfected = false;
        timeInfected = 0;
        this.fatalRecover = fatalRecover;
        isDead = false;
    }

    public boolean isInfected()
    {
        return isInfected;
    }

    public void infect()
    {
        isInfected = true;
        timeInfected = 0;
    }

    @Override
    public void update()
    {
        Agent neighbor = world.getNeighbor(this, 10);

        if (neighbor instanceof Plague)
        {
            Plague p = (Plague) neighbor;

            if ((p.isInfected() && !this.isInfected()) && !p.isDead)
            {
                int n = Utilities.rng.nextInt(100);
                if (n < PlagueSimulation.VIRULENCE)
                {
                    this.infect();
                }
            }
        }

        if (isInfected)
        {
            timeInfected++;
        }

        if ((timeInfected >= fatalRecover) && isInfected)
        {
            if (!isDead)
            {
                PlagueSimulation pr = (PlagueSimulation) world;
                int r = Utilities.rng.nextInt(2);
                if (r == 0 || !pr.getFatal())
                {
                    isInfected = false;
                    isDead = false;
                }
                else
                {
                    isDead = true;
                }
            }
        }

        if (!isDead)
        {
            move(1);
        }
    }

    private void setColor(Color color)
    {
        this.setColor(color);
    }
}
