package simulation.elements;

class MockInput extends Input {
    MockInput() {
        super(null);
        this.currentFlow = 0.f;
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
    }
}

