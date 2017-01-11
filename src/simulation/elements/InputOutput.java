package simulation.elements;

import util.Point;

public abstract class InputOutput implements java.io.Serializable {
    private static final double CLICK_RADIUS = 10;
    float currentFlow;
    Pipeline pipeline = null;
    Component component;
    private Point position;

    InputOutput(Component component, Point position) {
        this.component = component;
        this.position = position;
    }

    protected abstract void recalculateFlow(float previousElementFlow);

    public boolean inBoundingArea(Point point) {
        assert point != null;

        return position.getDistance(point) <= CLICK_RADIUS;
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
}
