package SimStation;

import mvc.Command;
import mvc.Utilities;

public class StatsCommand extends Command {
    public StatsCommand(mvc.Model model) {
        super(model);
    }

    public void execute() {
        if (model instanceof World) {
            World w = (World) model;
            String stats = "Clock: " + w.getClock() + "\n" +
                    "Agents Alive: " + w.getAlive();
            Utilities.inform(stats);
        }
    }
}
