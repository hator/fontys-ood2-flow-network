package simulation.elements;

class MockFixedFlowPipeline extends Pipeline {
    private float setFlow;

    MockFixedFlowPipeline(float flow, Input inputA) {
        super(null, inputA, flow);
        this.setFlow = flow;
        this.currentFlow = flow;
    }

    @Override
    float recalculateFlow() {
        return this.setFlow;
    }
}
