package SimStation;

import mvc.Command;

public class StopCommand extends Command {
    public StopCommand(mvc.Model model) {
        super(model);
    }

    public void execute() {
        if (model instanceof World) {
            ((World)model).stopAgents();
        }
    }
}
