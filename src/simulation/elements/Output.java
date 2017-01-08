package simulation.elements;

public class Output extends InputOutput implements java.io.Serializable{

    public Output(Component component) {
        super(component);
    }

    @Override
    protected void recalculateFlow(float previousElementFlow) {
        this.currentFlow = previousElementFlow;
        if(this.pipeline != null) {
            this.pipeline.recalculateFlow();
        }
    }
}
