package plague;

import mvc.*;
import SimStation.*;

public class InitialPercentInfectedCommand extends Command {
    World model;
    String type;
    Object source;
    Integer value = null;

    public InitialPercentInfectedCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Initial % Infected(0-100): ");
            value = Integer.valueOf(response);
            if (value > 100) { value = 100; }
            if (value < 0) { value = 0; }
        }

        PlagueSimulation m = (PlagueSimulation) model;
        m.setInitialInfectedPercent(value);

        model.changed();
    }
}
