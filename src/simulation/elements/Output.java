package simulation.elements;

import util.Point;

public class Output extends InputOutput implements java.io.Serializable{

    public Output(Component component, Point position) {
        super(component, position);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        if(this.pipeline != null) {
            this.pipeline.recalculateFlow();
        }
    }
}
