package plague;
import mvc.Command;
import mvc.Model;
import SimStation.World;

public class NotFatalCommand extends Command{
    World model;
    String type;
    Object source;

    public NotFatalCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{

        PlagueSimulation p = (PlagueSimulation) model;
        p.setFatal(false);

        model.changed();
    }

}
