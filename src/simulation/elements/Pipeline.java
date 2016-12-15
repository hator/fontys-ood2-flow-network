package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

public class Pipeline extends Element {
    private List<Point> points = new ArrayList<>();
    private Input input;
    private Output output;

    Pipeline(Output output, Input input, float maxFlow) {
        assert input != null;
        assert output != null;

        this.output = output;
        this.input = input;
        this.maxFlow = maxFlow;

        input.attachPipeline(this);
        output.attachPipeline(this);
    }

    // TODO builder or addPoint method?

    @Override
    void recalculateFlow() {
        this.currentFlow = output.getFlow();
        input.recalculateFlow(this.currentFlow);
    }

    public void detach() {
        input.detachPipeline();
        input.recalculateFlow(0);
        output.detachPipeline();
    }
}
