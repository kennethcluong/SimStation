package mvc;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener {
    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;  // control panel
    private JFrame frame;
    public static int FRAME_WIDTH = 900;
    public static int FRAME_HEIGHT = 600;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        view.setBackground((Color.GRAY));
        controlPanel = new JPanel();
        controlPanel.setBackground((Color.PINK));
        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);
        model.subscribe(this);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() { frame.setVisible(true); }

    public void update() { /* override in extensions if needed */ }

    public Model getModel() { return model; }


    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);  // view unsubscribes to old model and subscribes to the new one
        model.changed();
        // alternatively: this.model.copy(model);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, menu, and help menus
        JMenu fileMenu = Utilities.makeMenu("File", new String[] {"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            switch (cmmd) {
                case "Save" -> Utilities.save(model, false);
                case "SaveAs" -> Utilities.save(model, true);
                case "Open" -> {
                    Model newModel = Utilities.open(model);
                    if (newModel != null) setModel(newModel);
                }
                case "New" -> {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    // needed cuz setModel sets to true:
                    model.setUnsavedChanges(false);
                }
                case "Quit" -> {
                    Utilities.saveChanges(model);
                    System.exit(0);
                }
                case "About" -> Utilities.inform(factory.about());
                case "Help" -> Utilities.inform(factory.getHelp());
                default -> {
                    Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
                    command.execute();
                }
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}