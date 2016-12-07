package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

public class Pipeline extends Element {
    private List<Point> points = new ArrayList<>();
    private Input input;
    private Output output;

    Pipeline(Output output, Input input, float maxFlow) {
        this.output = output;
        this.input = input;
        this.maxFlow = maxFlow;

        input.attachPipeline(this);
    }

    // TODO builder or addPoint method?

    @Override
    float recalculateFlow() {
        return output.getFlow();
    }
}
