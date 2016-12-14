package simulation.elements;

import simulation.Settings;

class AdjustableSplitter extends Splitter {
    private float splitRatio;

    AdjustableSplitter() {
        this(0.5f);
    }

    AdjustableSplitter(float splitRatio) {
        this.splitRatio = splitRatio;
    }

    @Override
    void applySettings(Settings settings) {
        super.applySettings(settings);
        // TODO probably check if settings.splitRatio is not null
        this.splitRatio = settings.splitRatio;
    }

    @Override
    float recalculateFlow() {
        currentFlow = input.getFlow();
        outputA.setFlow(splitRatio * currentFlow);
        outputB.setFlow((1 - splitRatio) * currentFlow);
        return currentFlow;
    }
}
