package SimStation;

import mvc.Command;

public class PauseCommand extends Command {
    public PauseCommand(mvc.Model model) {
        super(model);
    }

    public void execute() {
        if (model instanceof World) {
            ((World)model).pauseAgents();
        }
    }
}
