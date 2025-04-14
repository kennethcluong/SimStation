package SimStation;

import mvc.Command;

public class ResumeCommand extends Command {
    public ResumeCommand(mvc.Model model) {
        super(model);
    }

    public void execute() {
        if (model instanceof World) {
            ((World)model).resumeAgents();
        }
    }
}

