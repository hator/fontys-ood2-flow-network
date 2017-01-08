package simulation.elements;

import simulation.Settings;
import util.Point;

public class AdjustableSplitter extends Splitter {
    private float splitRatio;

    AdjustableSplitter() {
        this(0.5f);
    }

    AdjustableSplitter(float splitRatio) {
        super(null,"res/adjustable-splitter100-100.png");
        this.splitRatio = splitRatio;
    }

    public AdjustableSplitter(float splitRatio, Point position) {
        super(position, "res/adjustable-splitter100-100.png");
        this.splitRatio = splitRatio;
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, splitRatio);
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
}
