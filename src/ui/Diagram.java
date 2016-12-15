package ui;

import simulation.SimulationFacade;
import simulation.Tool;
import util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Diagram extends JPanel {
    private Tool currentTool;
    private SimulationFacade simulation;

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

    private void mouseClickCallback(int x, int y) {
        assert currentTool != null;
        simulation.applyTool(new Point(x, y), currentTool);
    }

    void selectTool(Tool tool) {
        this.currentTool = tool;
    }
}
