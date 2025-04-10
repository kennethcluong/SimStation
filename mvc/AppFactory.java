package mvc;

public interface AppFactory {
    Model makeModel();  // could be public

    View makeView(Model model);  // could be public

    String[] getEditCommands();  // could be public

    Command makeEditCommand(Model model, String type, Object source);  // could be public

    String getTitle();  // could be public

    String[] getHelp();  // could be public

    String about();  // could be public
}
