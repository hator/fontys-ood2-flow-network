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
