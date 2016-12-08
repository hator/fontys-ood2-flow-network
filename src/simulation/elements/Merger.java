package simulation.elements;

class Merger extends Component {
    private Input inputA = new Input(null);
    private Input inputB = new Input(null);
    private Output output = new Output(this);

    @Override
    float recalculateFlow() {
        return inputA.getFlow() + inputB.getFlow();
    }

    Input getInputA() {
        return inputA;
    }

    Input getInputB() {
        return inputB;
    }
}
