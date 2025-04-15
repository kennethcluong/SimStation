package SimStation;

import mvc.Command;

public class StartCommand extends Command {
    public StartCommand(mvc.Model model) {
        super(model);
    }

    public void execute() {
        if (model instanceof World) {
            ((World)model).startAgents();
        }
    }
}
