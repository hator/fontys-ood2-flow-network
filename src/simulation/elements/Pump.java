package simulation.elements;

import java.util.ArrayList;
import java.util.List;

class Pump extends Component {
    private Output output = new Output(this);

    Pump(float currentFlow, float maxFlow) {
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
    }

    Output getOutput() {
        return output;
    }

    @Override
    void recalculateFlow() {
        this.getOutput().recalculateFlow(this.currentFlow);
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(output.pipeline);

        return pipelines;
    }
}
