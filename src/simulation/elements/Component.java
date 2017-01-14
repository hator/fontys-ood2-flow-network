package simulation.elements;

import simulation.Settings;
import util.Point;

import java.awt.*;
import java.util.List;

abstract public class Component extends Element implements java.io.Serializable{
    static final int CLICK_RADIUS = 50;
    private static final Point FLOW_LABEL_OFFSET = new Point(30, 60);
    private Point position;
    private boolean toBeDeleted = false;

    public Component(Settings settings, Point position) {
        super(settings);
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

    public abstract void renderInputsAndOutputs(Graphics graphics);

    Point calculateSingleIOPosition(boolean isInput) {
        return position.plus(new Point(isInput ? -20 : +20, 0));
    }

    Point calculateOneOfTwoIOsPosition(boolean isInput, int ioNumber) {
        assert ioNumber >= 1 && ioNumber <= 2;
        return calculateSingleIOPosition(isInput).plus(new Point(0, (ioNumber - 1) * 30 - 15));
    }

    void renderFlow(Graphics g) {
        Point flowLabelPosition = getPosition().plus(FLOW_LABEL_OFFSET);
        renderFlowAtPosition(g, settings, flowLabelPosition);
    }
}
