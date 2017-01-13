package simulation.elements;

import util.Point;

import java.awt.*;

public abstract class InputOutput implements java.io.Serializable {
    static final int RADIUS = 10;
    float currentFlow;
    Pipeline pipeline = null;
    Component component;
    Point position;

    InputOutput(Component component, Point position) {
        this.component = component;
        this.position = position;
    }

    protected abstract void recalculateFlow(float previousElementFlow);

    abstract void render(Graphics g);

    public boolean inBoundingArea(Point point) {
        assert point != null;

        return position.getDistance(point) <= RADIUS;
    }

    void attachPipeline(Pipeline pipeline) {
        // TODO check if there is already a pipeline?
        this.pipeline = pipeline;
    }

    float getFlow() {
        return currentFlow;
    }

    void detachPipeline() {
        pipeline = null;
    }

    public boolean pipelineConnected(){
        return pipeline != null;
    }
}
