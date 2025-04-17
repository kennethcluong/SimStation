
package plague;

import mvc.*;
import SimStation.*;

public class InitialPopulationCommand extends Command {
    World model;
    String type;
    Object source;
    Integer value = null;

    public InitialPopulationCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Initial Population Size(0-200): ");
            value = Integer.valueOf(response);
            if (value > 200) { value = 200; }
            if (value < 0) { value = 0; }
        }

        PlagueSimulation m = (PlagueSimulation) model;
        m.setInitialPopulationSize(value);

        model.changed();
    }
}
