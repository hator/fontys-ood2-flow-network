package simulation.elements;

import util.Point;

class MockInput extends Input {
    MockInput() {
        super(null, Point.zero());
        this.currentFlow = 0.f;
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
    }
}

