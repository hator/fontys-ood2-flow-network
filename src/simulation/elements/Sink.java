package simulation.elements;

class Sink extends Component {
    private Input input = new Input(null);

    Sink(float maxFlow) {
        this.maxFlow = maxFlow;
    }

    @Override
    float recalculateFlow() {
        return input.getFlow();
    }

    Input getInput() {
        return input;
    }
}
