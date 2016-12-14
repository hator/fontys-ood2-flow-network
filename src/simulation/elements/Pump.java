package simulation.elements;

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
}
