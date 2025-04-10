package mvc;

public abstract class Command {
    protected Model model;

    // Constructor
    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute() throws Exception;
}
