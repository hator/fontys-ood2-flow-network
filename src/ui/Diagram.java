package ui;

import simulation.Tool;

import javax.swing.*;
import java.awt.*;

class Diagram extends JPanel {
    private Tool currentTool = Tool.Select;

    Diagram() {
        Dimension dimension = new Dimension();
        dimension.setSize(800, 600);
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO draw the network
    }

    // TODO add mouse click callback

    void selectTool(Tool tool) {
        this.currentTool = tool;
    }
}
