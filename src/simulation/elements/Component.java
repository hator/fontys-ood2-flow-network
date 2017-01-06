package simulation.elements;

import util.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

abstract public class Component extends Element {
    static final int CLICK_RADIUS = 30;
    private Point position;
    private boolean toBeDeleted = false;
    private BufferedImage image;


    Component(Point position, String imageName)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            System.err.println("File not found");

            //TODO error message + exception
        }
        this.image = image;
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

    public BufferedImage getImage(){
        return image;
    }
    public void setToBeDeleted() {
        toBeDeleted = true;
    }

    boolean isBeingDeleted() {
        return toBeDeleted;
    }
}
