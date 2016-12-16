package simulation.elements;

class Output extends InputOutput {

    Output(Component component) {
        super(component);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        if(this.pipeline != null) {
            this.pipeline.recalculateFlow();
        }
    }

    protected void recalculateFlow() {
        component.recalculateFlow();
        if(this.pipeline != null) {
            this.pipeline.recalculateFlow();
        }
    }
}
