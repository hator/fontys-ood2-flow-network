package simulation.elements;

import simulation.Settings;
import util.Point;

import java.awt.*;

abstract public class Element implements java.io.Serializable {
    float currentFlow;
    float maxFlow;

    abstract void recalculateFlow();

    float getFlow() {
        return currentFlow;
    }

    abstract public Settings getSettings();

    void applySettings(Settings settings) {
        this.currentFlow = settings.currentFlow;
        this.maxFlow = settings.maxFlow;
    }

    public abstract boolean inBoundingArea(Point point);

    public abstract void render(Graphics graphics);
}
