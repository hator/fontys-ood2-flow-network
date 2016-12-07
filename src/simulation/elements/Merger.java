package simulation.elements;

class Merger extends Component {
    private Input inputA;
    private Input inputB;
    private Output output;

    @Override
    float recalculateFlow() {
        return inputA.getFlow() + inputB.getFlow();
    }
}
