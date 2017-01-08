package simulation.elements;

public class Input extends InputOutput implements java.io.Serializable{

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
