package simulation.elements;

import simulation.Settings;
import util.Point;

import java.awt.*;

abstract public class Element implements java.io.Serializable {
    Settings settings;

    Element(Settings settings) {
        this.settings = settings;
    }

    abstract void recalculateFlow();

    float getFlow() {
        return settings.currentFlow;
    }

    public Settings getSettings() {
        return settings;
    }

    public abstract boolean inBoundingArea(Point point);

    public abstract void render(Graphics graphics);
}
