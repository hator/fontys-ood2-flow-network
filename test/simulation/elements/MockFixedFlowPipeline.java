package simulation.elements;

import util.Point;

class MockFixedFlowPipeline extends Pipeline {
    private final Input input;

    MockFixedFlowPipeline(float flow, Input input) {
        super(new Output(null, Point.zero()), input, flow);
        this.settings.currentFlow = flow;
        this.input = input;
    }

    @Override
    void recalculateFlow() {
        // trigger recalculation of the next element (input)
        input.recalculateFlow(this.settings.currentFlow);
    }
}
