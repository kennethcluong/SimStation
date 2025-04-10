package mvc;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
// import javax.swing.border.LineBorder;
import java.awt.Color;

public class View extends JPanel implements Subscriber {
    protected Model model;
    // static public Dimension dim;

    public View(Model model) {
        super();
        this.model = model;
        model.subscribe(this);
        // optional border around the view component
        // setBorder(LineBorder.createGrayLineBorder());//.createBlackLineBorder());
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
        setBackground(Color.LIGHT_GRAY);
    }

    public Model getModel() { return model; }

    // called by File/Open and File/New
    public void setModel(Model newModel) {
        if (model != null) {
            model.unsubscribe(this);
        }
        this.model = newModel;
        if (newModel != null) {
            model.subscribe(this);
            update();
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
