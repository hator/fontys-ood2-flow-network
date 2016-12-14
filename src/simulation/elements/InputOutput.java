package simulation.elements;

abstract class InputOutput {
    float currentFlow;
    Pipeline pipeline = null;
    Component component;

    InputOutput(Component component) {
        this.component = component;
    }

    protected abstract void recalculateFlow(float previousElementFlow);

    void attachPipeline(Pipeline pipeline) {
        // TODO check if there is already a pipeline?
        this.pipeline = pipeline;
    }

    float getFlow() {
        return currentFlow;
    }

    void deattachPipeline() {
        pipeline = null;
    }
}
