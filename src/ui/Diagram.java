package ui;

import simulation.Result;
import simulation.Settings;
import simulation.SimulationFacade;
import simulation.Tool;
import util.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        Dimension dim = getSize();

        //Draw background color
        g.setColor(Color.WHITE);
        g.fillRect(0,0,dim.width,dim.height);

        simulation.render(g);
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
            System.out.println(newElementSettings.currentFlow +" "+newElementSettings.maxFlow+" "+ newElementSettings.splitRatio);
                if(simulation.applyTool(point, currentTool, newElementSettings) == Result.ComponentsOverlapping){
                   System.err.println("Component Overlap");
                }
                break;
        }
        this.repaint();
    }

    void selectTool(Tool tool, Settings settings) {
        currentTool = tool;
        currentSettingsReference = settings;
    }
}
