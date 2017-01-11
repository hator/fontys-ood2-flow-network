package simulation.elements;

import util.Point;

public class Input extends InputOutput implements java.io.Serializable{

    public Input(Component component, Point position) {
        super(component, position);
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
