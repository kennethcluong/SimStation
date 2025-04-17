package SimStation.plague;

import mvc.Model;
import mvc.AppFactory;
import mvc.View;
import mvc.Command;
import simStation.SimStationFactory;

import javax.swing.JSlider;

public class PlagueFactory extends SimStationFactory {
    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }

    @Override
    public View makeView(Model model) {
        return new PlagueView(model);
    }

    @Override
    public String getTitle() {
        return "Plague Simulator";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click on the buttons below to get started"};
    }

    @Override
    public String about() {
        return "Plague Simulation Version 1.0 by SimStation Group .";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats", "Initial % Infected", "Infection Probability", "Initial Population Size", "Fatality/Recovery Time", "Not Fatal"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmmd = super.makeEditCommand(model, type, source);

        if (cmmd == null) {
            if (type.equals("Initial % Infected")) {
                cmmd = new InitialPercentInfectedCommand(model, type, source);
                if (source instanceof JSlider) {
                    ((InitialPercentInfectedCommand) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Infection Probability")) {
                cmmd = new InfectionProbabilityCommand(model, type, source);
                if (source instanceof JSlider) {
                    ((InfectionProbabilityCommand) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Initial Population Size")) {
                cmmd = new InitialPopulationCommand(model, type, source);
                if (source instanceof JSlider) {
                    ((InitialPopulationCommand) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Fatality/Recovery Time")) {
                cmmd = new FatalityRecoveryCommand(model, type, source);
                if (source instanceof JSlider) {
                    ((FatalityRecoveryCommand) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Not Fatal")) {
                cmmd = new NotFatalCommand(model, type, source);
            }
        }

        return cmmd;
    }
}
