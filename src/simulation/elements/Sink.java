package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

public class Sink extends Component {
    private Input input = new Input(this);

    Sink(float maxFlow) {
        super(null, "res/barrel100-100.png");
        this.maxFlow = maxFlow;
    }

   public Sink(float maxFlow, Point position) {
        super(position, "res/barrel100-100.png");
        this.maxFlow = maxFlow;
    }

    Input getInput() {
        return input;
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(input.pipeline);

        return pipelines;
    }
}
