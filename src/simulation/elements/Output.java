package simulation.elements;

import util.Point;

import java.awt.*;

public class Output extends InputOutput implements java.io.Serializable{

    public Output(Component component, Point position) {
        super(component, position);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        if(this.pipeline != null) {
            this.pipeline.recalculateFlow();
        }
    }

    @Override
    void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(position.x - RADIUS, position.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
