package simulation.elements;

import util.Point;

import java.util.List;

abstract public class Component extends Element {
    public static final int clickRadius = 30;
    private Point position;
    private boolean toBeDeleted = false;

    Component(Point position)
    {
        this.position = position;
    }

    public abstract List<Pipeline> getPipelines();

    public boolean isOverlaping(Component component){
        assert component != null;

        return position.getDistance(component.getPosition()) <= 2*clickRadius;
    }

    @Override
    public boolean inBoundingArea(Point point) {
        assert point!= null;

        return position.getDistance(point) <= clickRadius;
    }

    public Point getPosition(){return position;}

    public void setToBeDeleted() {
        toBeDeleted = true;
    }

    public boolean isBeingDeleted() {
        return toBeDeleted;
    }
}
