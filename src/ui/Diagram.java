package ui;

import javax.swing.*;
import java.awt.*;

class Diagram extends JPanel {

    Diagram() {
        Dimension dimension = new Dimension();
        dimension.setSize(800, 600);
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(0, 0, 100, 200);
    }
}
