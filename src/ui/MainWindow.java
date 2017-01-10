package ui;

import simulation.SimulationFacade;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class MainWindow extends JFrame {

    private SimulationFacade simulation;
    private Diagram diagram;

    MainWindow() {
        super("Flow Network Simulator");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int returnVal = JOptionPane.showConfirmDialog(e.getWindow(), "Do you want to save?");
                if(returnVal == JOptionPane.YES_OPTION) {
                    showSaveFileDialog();
                    e.getWindow().dispose();
                }
                else if(returnVal == JOptionPane.NO_OPTION){
                    e.getWindow().dispose();
                }
                //Do nothing on Cancel
            }
        });

        setMinimumSize(new Dimension(800, 600));
        simulation = new SimulationFacade();

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        diagram = new Diagram(simulation);
        add(diagram, BorderLayout.CENTER);

        final Toolbox toolbox = new Toolbox(diagram::selectTool, new SaveFile(), new OpenFile());
        add(toolbox, BorderLayout.WEST);

        diagram.setChangeSettingsReferenceCallback(toolbox::setCurrentSettingsReference);

        pack(); // resize the window
    }

    private class OpenFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showOpenFileDialog();
        }
    }
    private class SaveFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showSaveFileDialog();
        }
    }

    private void showOpenFileDialog(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Flow Network Files", "fn");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(MainWindow.this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fileIn = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
                simulation.loadFlowNetwork(fileIn);

            } catch(FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "This file can not be found.", "File not found!", JOptionPane.ERROR_MESSAGE);
            }
            diagram.repaint();
        }
    }

    private void showSaveFileDialog(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Flow Network Files", "fn");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(MainWindow.this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOut;
                if (!chooser.getSelectedFile().getAbsolutePath().endsWith(".fn")) {
                    fileOut = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath() + ".fn");
                } else {
                    fileOut = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath());
                }
                simulation.saveFlowNetwork(fileOut);
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(this, "This file can not be found.", "File not found!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
