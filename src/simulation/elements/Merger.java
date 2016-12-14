package simulation.elements;

class Merger extends Component {
    private Input inputA = new Input(this);
    private Input inputB = new Input(this);
    private Output output = new Output(this);

    Input getInputA() {
        return inputA;
    }

    Input getInputB() {
        return inputB;
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = inputA.getFlow() + inputB.getFlow();
        this.output.recalculateFlow(this.currentFlow);
    }

    Output getOutput() {
        return output;
    }
}
