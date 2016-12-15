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
                JOptionPane.showConfirmDialog(e.getWindow(), "GOWNO");
                e.getWindow().dispose();
            }
        });

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        final Toolbox toolbox = new Toolbox();
        add(toolbox, BorderLayout.WEST);

        final Diagram diagram = new Diagram();
        add(diagram, BorderLayout.CENTER);

        pack(); // resize the window
    }
}
