package simulation.elements;

class Pump extends Component {
    private Output output = new Output(this);

    Pump(float currentFlow, float maxFlow) {
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
    }

    @Override
    float recalculateFlow() {
        getOutput().setFlow(currentFlow);
        return currentFlow; // Nothing to do, currentFlow is always up-to-date
    }

    Output getOutput() {
        return output;
    }
}
