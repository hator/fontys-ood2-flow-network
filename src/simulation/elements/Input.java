package simulation.elements;

public class Input extends InputOutput {

    public Input(Component component) {
        super(component);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        this.component.recalculateFlow();
    }

    boolean isComponentBeingDeleted() {
        return component.isBeingDeleted();
    }
}
