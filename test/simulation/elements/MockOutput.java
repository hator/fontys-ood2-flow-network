package simulation.elements;

class MockOutput extends Output {
    private float setFlow;

    MockOutput(float flow) {
        super(null);
        this.setFlow = flow;
        this.currentFlow = flow;
    }

    @Override
    protected void recalculateFlow() {
        this.currentFlow = setFlow;
    }
}
