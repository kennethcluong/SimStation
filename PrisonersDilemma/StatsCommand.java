package PrisonersDilemma;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StatsCommand extends Command {
    public StatsCommand(Model m) { super(m); }
    @Override
    public void execute() {
        Utilities.inform(((PDModel)model).getStatistics());
    }
}
