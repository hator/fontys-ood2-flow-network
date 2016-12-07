package simulation.elements;

class Input extends InputOutput {
    private Pipeline pipeline;

    Input(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    protected void recalculateFlow() {
        if(pipeline != null) {
            currentFlow = pipeline.getFlow();
        } else {
            currentFlow = 0;
        }
    }

    void attachPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }
}
