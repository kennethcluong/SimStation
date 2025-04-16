package PrisonersDilemma;

import mvc.AppFactory;
import mvc.Model;
import mvc.View;
import mvc.Command;
import SimStation.StartCommand;
import SimStation.PauseCommand;
import SimStation.ResumeCommand;
import SimStation.StopCommand;

public class PDFactory implements AppFactory {
    @Override public Model    makeModel()                   { return new PDModel(); }
    @Override public View     makeView(Model m)             { return new PDWorldView(m); }
    @Override public String[] getEditCommands()             {
        return new String[] { "Start", "Pause", "Resume", "Stop", "Stats" };
    }
    @Override public Command  makeEditCommand(Model m, String type, Object src) {
        return switch(type) {
            case "Start"  -> new StartCommand(m);
            case "Pause"  -> new PauseCommand(m);
            case "Resume" -> new ResumeCommand(m);
            case "Stop"   -> new StopCommand(m);
            case "Stats"  -> new StatsCommand(m);
            default       -> null;
        };
    }
    @Override public String   getTitle()                     { return "Prisoner's Dilemma"; }
    @Override public String[] getHelp()                      {
        return new String[] {
                "Use Start/Pause/Resume/Stop to control the tournament.",
                "Stats shows the current average fitness of each strategy."
        };
    }
    @Override public String   about()                        { return "PD Tournament v1.0"; }
}
