package ui;

import simulation.SimulationFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainWindow extends JFrame {

    private SimulationFacade simulation;

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

        setMinimumSize(new Dimension(800, 600));
        simulation = new SimulationFacade();

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        final Diagram diagram = new Diagram(simulation);
        add(diagram, BorderLayout.CENTER);

        final Toolbox toolbox = new Toolbox(diagram::selectTool);
        add(toolbox, BorderLayout.WEST);

        diagram.setChangeSettingsReferenceCallback(toolbox::setCurrentSettingsReference);

        pack(); // resize the window
    }
}
