package simulation.elements;

import util.Point;

class FixedSplitter extends Splitter {

    FixedSplitter() {
        super(null);
    }

    FixedSplitter(Point position){
        super(position);
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
        this.outputA.recalculateFlow(currentFlow/2);
        this.outputB.recalculateFlow(currentFlow/2);
    }
}
