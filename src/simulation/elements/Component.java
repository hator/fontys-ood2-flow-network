package simulation.elements;

import util.Point;

import java.util.List;

abstract public class Component extends Element {
    static final int CLICK_RADIUS = 30;
    private Point position;
    private boolean toBeDeleted = false;

    Component(Point position)
    {
        this.position = position;
    }

    public abstract List<Pipeline> getPipelines();

    public boolean isOverlapping(Component component) {
        assert component != null;

        return position.getDistance(component.getPosition()) <= 2 * CLICK_RADIUS;
    }

    @Override
    public boolean inBoundingArea(Point point) {
        assert point!= null;

        return position.getDistance(point) <= CLICK_RADIUS;
    }

    public Point getPosition() {
        return position;
    }

    public void setToBeDeleted() {
        toBeDeleted = true;
    }

    boolean isBeingDeleted() {
        return toBeDeleted;
    }
}
