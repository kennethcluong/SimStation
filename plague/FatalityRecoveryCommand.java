package simStation.plague;

import mvc.*;
import simStation.*;

public class FatalityRecoveryCommand extends Command {
    World model;
    String type;
    Object source;
    Integer value = null;

    public FatalityRecoveryCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Set Fatality/Recovery Time(0-500): ");
            value = Integer.valueOf(response);
            if (value > 500) { value = 500; }
            if (value < 0) { value = 0; }
        }

        PlagueSimulation m = (PlagueSimulation) model;
        m.setFatalRecovery(value);

        model.changed();
    }
}
