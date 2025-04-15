package SimStation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimStationFactory implements AppFactory {

    public Model makeModel() {
        // Return an instance of the simulation world.
        return new SimStationWorld();
    }

    public View makeView(Model model) {
        return new WorldView(model);
    }

    public String[] getEditCommands() {
        // Define available simulation control commands.
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        // Return a command instance based on the type.
        if (!(model instanceof World)) {
            return null;
        }
        World world = (World) model;
        switch (type) {
            case "Start":
                return new StartCommand(world);
            case "Pause":
                return new PauseCommand(world);
            case "Resume":
                return new ResumeCommand(world);
            case "Stop":
                return new StopCommand(world);
            case "Stats":
                return new StatsCommand(world);
            default:
                return null;
        }
    }

    public String getTitle() {
        return "SimStation";
    }

    public String[] getHelp() {
        return new String[] {
                "SimStation is an agent-based simulation framework.",
                "Use the buttons to Start, Pause, Resume, Stop the simulation.",
                "Stats shows simulation details."
        };
    }

    public String about() {
        return "SimStation (Version 1.0)\nDeveloped as part of a simulation framework assignment.";
    }
}
