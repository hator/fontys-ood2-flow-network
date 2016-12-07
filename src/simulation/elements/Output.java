package simulation.elements;

class Output extends InputOutput {
    private Component component;

    Output(Component component) {
        this.component = component;
    }

    void setFlow(float flow) {
        currentFlow = flow;
    }

    @Override
    protected void recalculateFlow() {
        component.recalculateFlow();
    }
}
