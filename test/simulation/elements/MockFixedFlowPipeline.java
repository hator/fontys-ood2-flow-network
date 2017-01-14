package simulation.elements;

import simulation.Settings;
import util.Point;

import java.util.Collections;

class MockFixedFlowPipeline extends Pipeline {
    private final Input input;

    MockFixedFlowPipeline(float flow, Input input) {
        super(new Output(null, Point.zero()), input, Collections.emptyList(), new Settings(flow, flow, null));
        this.input = input;
    }

    @Override
    public void recalculateFlow() {
        // trigger recalculation of the next element (input)
        input.recalculateFlow(this.settings.getCurrentFlow());
    }
}
