package simulation.elements;

import simulation.Settings;
import util.Point;

import java.awt.*;

abstract public class Element implements java.io.Serializable {
    Settings settings;

    Element(Settings settings) {
        this.settings = settings;
    }

    public abstract void recalculateFlow();

    float getFlow() {
        return settings.currentFlow;
    }

    public Settings getSettings() {
        return settings;
    }

    public abstract boolean inBoundingArea(Point point);

    public abstract void render(Graphics graphics);

    static void renderFlowAtPosition(Graphics g, Settings settings, Point position) {
        if (settings.currentFlow > settings.maxFlow) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(Float.toString(settings.currentFlow), position.x, position.y);
    }
}
