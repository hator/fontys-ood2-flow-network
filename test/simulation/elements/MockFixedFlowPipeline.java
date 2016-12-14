package simulation.elements;

class MockFixedFlowPipeline extends Pipeline {
    private final Input input;

    MockFixedFlowPipeline(float flow, Input input) {
        super(new Output(null), input, flow);
        this.currentFlow = flow;
        this.input = input;
    }

    @Override
    void recalculateFlow() {
        // trigger recalculation of the next element (input)
        input.recalculateFlow(this.currentFlow);
    }
}
