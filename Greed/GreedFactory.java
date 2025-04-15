package SimStation.Greed;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;
import SimStation.World;
import SimStation.StartCommand;
import SimStation.PauseCommand;
import SimStation.ResumeCommand;
import SimStation.StopCommand;
import SimStation.StatsCommand;

public class GreedFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Meadow();
    }

    @Override
    public View makeView(Model model) {
        // Return our specialized GreedView
        return new GreedView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (!(model instanceof World)) return null;
        World world = (World) model;
        return switch (type) {
            case "Start"  -> new StartCommand(world);
            case "Pause"  -> new PauseCommand(world);
            case "Resume" -> new ResumeCommand(world);
            case "Stop"   -> new StopCommand(world);
            case "Stats"  -> new StatsCommand(world);
            default       -> null;
        };
    }

    @Override
    public String getTitle() {
        return "Greed Simulation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Greed simulates the Tragedy of the Commons.",
                "Cows eat grass based on greed. Grass grows back at a rate you control.",
                "Adjust move energy to see if cows relocate or starve."
        };
    }

    @Override
    public String about() {
        return "Greed Simulation (Version 1.0)\nBuilt on SimStation2.";
    }
}
