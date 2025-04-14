package SimStation;

import mvc.AppPanel;
import mvc.Model;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Iterator;

public class WorldPanel extends AppPanel {
    public JPanel threadPanel = new JPanel();

    public WorldPanel(SimStationFactory factory) {
        super(factory);

        threadPanel.setLayout(new GridLayout(1, 5));
        threadPanel.setOpaque(false);

        // Add control buttons.
        JPanel p = new JPanel();
        p.setOpaque(false);
        JButton button = new JButton("Start");
        button.addActionListener(this);
        p.add(button);
        threadPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        button = new JButton("Pause");
        button.addActionListener(this);
        p.add(button);
        threadPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        button = new JButton("Resume");
        button.addActionListener(this);
        p.add(button);
        threadPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        button = new JButton("Stop");
        button.addActionListener(this);
        p.add(button);
        threadPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        button = new JButton("Stats");
        button.addActionListener(this);
        p.add(button);
        threadPanel.add(p);

        controlPanel.setLayout(new BorderLayout());
        p = new JPanel();
        p.setOpaque(false);
        p.add(threadPanel);
        controlPanel.add(p, BorderLayout.NORTH);
    }

    public void setModel(Model m) {
        super.setModel(m);
        if (m instanceof World) {
            World w = (World) m;
            Iterator<Agent> it = w.iterator();
            while (it.hasNext()) {
                Thread t = new Thread(it.next());
                t.start();
            }
        }
    }

    public void update() {
        // Optionally update the panel (e.g., refresh stats).
    }
}
