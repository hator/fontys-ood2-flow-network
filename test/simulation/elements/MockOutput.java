package simulation.elements;

import util.Point;

class MockOutput extends Output {
    private float setFlow;

    MockOutput(float flow) {
        super(null, Point.zero());
        this.setFlow = flow;
        this.currentFlow = flow;
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = setFlow;
    }
}
