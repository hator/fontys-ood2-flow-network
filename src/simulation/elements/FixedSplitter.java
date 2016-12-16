package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

class FixedSplitter extends Splitter {

    FixedSplitter() {
        super(null);
    }

    FixedSplitter(Point position){
        super(position);
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
        this.outputA.recalculateFlow(currentFlow/2);
        this.outputB.recalculateFlow(currentFlow/2);
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(input.pipeline);
        pipelines.add(outputA.pipeline);
        pipelines.add(outputB.pipeline);

        return pipelines;
    }
}
