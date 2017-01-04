package ui;

import simulation.Settings;
import simulation.SimulationFacade;
import simulation.Tool;
import util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

class Diagram extends JPanel {
    private Tool currentTool;
    private Settings currentSettingsReference;
    private SimulationFacade simulation;
    private Consumer<Settings> changeSettingsReferenceCallback;

    Diagram(SimulationFacade simulation) {
        this.simulation = simulation;
        Dimension dimension = new Dimension();
        dimension.setSize(800, 600);
        setPreferredSize(dimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickCallback(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO draw the network


    }

    void setChangeSettingsReferenceCallback(Consumer<Settings> changeSettingsReferenceCallback) {
        this.changeSettingsReferenceCallback = changeSettingsReferenceCallback;
    }

    private void mouseClickCallback(int x, int y) {
        assert currentTool != null;

        Point point = new Point(x, y);

        switch (currentTool) {
            case Select:
                Settings settings = simulation.select(point);
                if (settings != null) {
                    changeSettingsReferenceCallback.accept(settings);
                } else {
                    // TODO error message?
                }
                break;
            case Remove:
                simulation.remove(point);
                break;
            default: // Add component
                Settings newElementSettings = currentSettingsReference;
                simulation.applyTool(point, currentTool, newElementSettings);
                break;
        }
    }

    void selectTool(Tool tool, Settings settings) {
        currentTool = tool;
        currentSettingsReference = settings;
    }
}
