package simulation.elements;

import simulation.Settings;
import util.Point;

import java.util.ArrayList;
import java.util.List;

class AdjustableSplitter extends Splitter {
    private float splitRatio;

    AdjustableSplitter() {
        this(0.5f);
    }

    AdjustableSplitter(float splitRatio) {
        super(null);
        this.splitRatio = splitRatio;
    }

    AdjustableSplitter(float splitRatio, Point position) {
        super(position);
        this.splitRatio = splitRatio;
    }

    @Override
    void recalculateFlow() {
        currentFlow = input.getFlow();
        outputA.recalculateFlow(splitRatio * currentFlow);
        outputB.recalculateFlow((1 - splitRatio) * currentFlow);
    }

    @Override
    void applySettings(Settings settings) {
        super.applySettings(settings);
        // TODO probably check if settings.splitRatio is not null
        this.splitRatio = settings.splitRatio;
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
