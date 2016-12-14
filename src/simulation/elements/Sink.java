package simulation.elements;

class Sink extends Component {
    private Input input = new Input(this);

    Sink(float maxFlow) {
        this.maxFlow = maxFlow;
    }

    Input getInput() {
        return input;
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
    }
}
