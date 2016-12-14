package simulation.elements;

class FixedSplitter extends Splitter {

    @Override
    float recalculateFlow() {
        if(input == null) {
            return 0;
        }
        currentFlow = input.getFlow();
        outputA.setFlow(0.5f * currentFlow);
        outputB.setFlow(0.5f * currentFlow);
        return currentFlow;
    }
}
