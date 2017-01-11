package simulation.elements;

import util.Point;

import java.awt.*;

public class Input extends InputOutput implements java.io.Serializable {

    public Input(Component component, Point position) {
        super(component, position);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        this.component.recalculateFlow();
    }

    @Override
    void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(position.x - RADIUS, position.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    boolean isComponentBeingDeleted() {
        return component.isBeingDeleted();
    }
}
