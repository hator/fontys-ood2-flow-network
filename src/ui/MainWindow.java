package ui;

import simulation.SimulationFacade;
import util.ConsumerWithIOException;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

class MainWindow extends JFrame {

    private SimulationFacade simulation;

    MainWindow() {
        super("Flow Network Simulator");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(showSaveOrCancelPrompt()) {
                    e.getWindow().dispose();
                } // on cancel do nothing
            }
        });

        setMinimumSize(new Dimension(800, 600));
        simulation = new SimulationFacade();

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        initializeMenuBar();

        final Diagram diagram = new Diagram(simulation);
        add(diagram, BorderLayout.CENTER);

        final Toolbox toolbox = new Toolbox(diagram::selectTool);
        add(toolbox, BorderLayout.WEST);

        diagram.setChangeSettingsReferenceCallback(toolbox::setCurrentSettingsReference);

        pack(); // resize the window
    }

    private JFileChooser getFlowNetworkFileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Flow Network Files", "fn");
        chooser.setFileFilter(filter);
        return chooser;
    }

    private void initializeMenuBar() {
        JMenuItem menuItem;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        menuItem = new JMenuItem("New");
        menuItem.addActionListener(this::handleNew);
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(this::handleOpen);
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Save As...");
        menuItem.addActionListener(this::handleSaveAs);
        fileMenu.add(menuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private void handleNew(ActionEvent e) {
        if (showSaveOrCancelPrompt()) {
            simulation.newFlowNetwork();
            repaint();
        }
    }

    private void handleOpen(ActionEvent e) {
        if (!showSaveOrCancelPrompt()) {
            return;
        }
        JFileChooser chooser = getFlowNetworkFileChooser();
        int chooserOption = chooser.showOpenDialog(MainWindow.this);
        if (chooserOption != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String path = chooser.getSelectedFile().getAbsolutePath();
        withInputFileStream(path, simulation::loadFlowNetwork);
        repaint();
    }

    private void handleSaveAs(ActionEvent e) {
        showSaveDialogAndSave();
    }

    private boolean showSaveDialogAndSave() {
        JFileChooser chooser = getFlowNetworkFileChooser();
        int chooserOption = chooser.showSaveDialog(MainWindow.this);
        if (chooserOption != JFileChooser.APPROVE_OPTION) {
            return false;
        }
        String path = chooser.getSelectedFile().getAbsolutePath();
        if (!path.endsWith(".fn")) {
            path += ".fn";
        }
        return withOutputFileStream(path, simulation::saveFlowNetwork);
    }

    /** Shows dialog asking whether to save, not save or cancel
     *
     * @return true if should proceed (either saved or not saved), false on cancel
     */
    private boolean showSaveOrCancelPrompt() {
        int returnVal = JOptionPane.showConfirmDialog(this, "Do you want to save?");
        if (returnVal == JOptionPane.YES_OPTION) {
            return showSaveDialogAndSave();
        } else if (returnVal == JOptionPane.NO_OPTION) {
            return true;
        }
        return false;
    }

    private void withInputFileStream(String path, ConsumerWithIOException<InputStream> action) {
        try (FileInputStream fileIn = new FileInputStream(path)) {
            action.apply(fileIn);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "This file can not be found.", "File not found!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading from file.", "Error reading from file!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean withOutputFileStream(String path, ConsumerWithIOException<OutputStream> action) {
        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            action.apply(fileOut);
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "This file can not be found.", "File not found!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file.", "Error writing to file!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
