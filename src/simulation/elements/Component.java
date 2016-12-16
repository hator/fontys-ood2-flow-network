package simulation.elements;

import util.Point;

import java.util.List;

abstract public class Component extends Element {
    public static final int clickRadius = 30;
    private Point position;

    Component(Point position)
    {
        this.position = position;
    }

    public abstract List<Pipeline> getPipelines();

    @Override
    public boolean inBoundingArea(Point point) {
        assert point!= null;

        return position.inPointRadius(point, clickRadius);
    }
}
