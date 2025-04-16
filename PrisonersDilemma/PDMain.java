package PrisonersDilemma;

import mvc.SafeFrame;
import mvc.Utilities;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create initial model, view, and control panel.
            PDModel model = new PDModel();
            PDView view = new PDView(model);
            PDPanel controlPanel = new PDPanel(model);

            // Create panel to hold both view and control panel.
            final JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(view, BorderLayout.CENTER);
            mainPanel.add(controlPanel, BorderLayout.SOUTH);

            // Create main application window.
            SafeFrame frame = new SafeFrame();
            frame.setTitle("Prisoner's Dilemma Tournament");
            frame.setContentPane(mainPanel);
            frame.setSize(600, 400);

            // File menu with options: New, Open, Save, Exit.
            String[] fileItems = { "New", "Open", "Save", "Exit" };
            ActionListener fileListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("New".equals(cmd)) {
                        // Create new simulation sheet:
                        PDModel newModel = new PDModel();
                        PDView newView = new PDView(newModel);
                        PDPanel newControlPanel = new PDPanel(newModel);

                        // Replace current content in mainPanel:
                        mainPanel.removeAll();
                        mainPanel.add(newView, BorderLayout.CENTER);
                        mainPanel.add(newControlPanel, BorderLayout.SOUTH);
                        mainPanel.revalidate();
                        mainPanel.repaint();

                        Utilities.log("New sheet created.");
                    } else if ("Open".equals(cmd)) {
                        Utilities.log("Open menu option selected.");
                        PDModel newModel = (PDModel) Utilities.open(model);
                        if (newModel != null) {
                            // Update view for newly loaded model:
                            mainPanel.removeAll();
                            PDView openView = new PDView(newModel);
                            PDPanel openControlPanel = new PDPanel(newModel);
                            mainPanel.add(openView, BorderLayout.CENTER);
                            mainPanel.add(openControlPanel, BorderLayout.SOUTH);
                            mainPanel.revalidate();
                            mainPanel.repaint();
                        }
                    } else if ("Save".equals(cmd)) {
                        Utilities.log("Save menu option selected.");
                        Utilities.save(model, false);
                    } else if ("Exit".equals(cmd)) {
                        System.exit(0);
                    }
                }
            };
            JMenu fileMenu = Utilities.makeMenu("File", fileItems, fileListener);

            // Edit menu.
            String[] editItems = { "Cut", "Copy", "Paste" };
            ActionListener editListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    Utilities.log("Edit (" + cmd + ") menu option selected.");
                }
            };
            JMenu editMenu = Utilities.makeMenu("Edit", editItems, editListener);

            // Help menu.
            String[] helpItems = { "About" };
            ActionListener helpListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("About".equals(e.getActionCommand())) {
                        JOptionPane.showMessageDialog(frame,
                                "Prisoner's Dilemma Tournament\nVersion 1.0",
                                "About",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            };
            JMenu helpMenu = Utilities.makeMenu("Help", helpItems, helpListener);

            // Create menu bar and add all menus.
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);
            frame.setJMenuBar(menuBar);

            // Display main window.
            frame.setVisible(true);
        });
    }
}