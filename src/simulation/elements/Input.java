package simulation.elements;

class Input extends InputOutput {

    Input(Component component) {
        super(component);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        this.component.recalculateFlow();
    }

    public boolean isComponentBeingDeleted() {
        return component.isBeingDeleted();
    }
}
