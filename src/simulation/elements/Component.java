package simulation.elements;

import util.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

abstract public class Component extends Element implements java.io.Serializable{
    static final int CLICK_RADIUS = 50;
    private Point position;
    private boolean toBeDeleted = false;

    Component(Point position)
    {
        this.position = position;
    }

    public abstract List<Pipeline> getPipelines();
    public abstract List<Input> getInputs();
    public abstract List<Output> getOutputs();

    public boolean isOverlapping(Component component) {
        assert component != null;

        return position.getDistance(component.getPosition()) <= 2 * CLICK_RADIUS;
    }

    @Override
    public boolean inBoundingArea(Point point) {
        assert point!= null;

        return position.getDistance(point) <= CLICK_RADIUS;
    }

    Point getPosition() {
        return position;
    }

    public void setToBeDeleted() {
        toBeDeleted = true;
    }

    boolean isBeingDeleted() {
        return toBeDeleted;
    }

    protected Point calculateSingleIOPosition(boolean isInput) {
        return position.plus(new Point(isInput ? -20 : +20, 0));
    }

    protected Point calculateOneOfTwoIOsPosition(boolean isInput, int ioNumber) {
        assert ioNumber >= 1 && ioNumber <= 2;
        return calculateSingleIOPosition(isInput).plus(new Point(0, ioNumber * 15 - 30));
    }
}
