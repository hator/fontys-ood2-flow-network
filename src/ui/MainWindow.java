package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainWindow extends JFrame {

    MainWindow() {
        super("Flow Network Simulator");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO add saving dialog
                // JOptionPane.showConfirmDialog(e.getWindow(), "Do you want to save?");
                e.getWindow().dispose();
            }
        });

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        final Diagram diagram = new Diagram();
        add(diagram, BorderLayout.CENTER);

        final Toolbox toolbox = new Toolbox(diagram::selectTool);
        add(toolbox, BorderLayout.WEST);

        pack(); // resize the window
    }
}
