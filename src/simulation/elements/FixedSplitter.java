package simulation.elements;

import simulation.Settings;
import util.Point;

public class FixedSplitter extends Splitter {

    FixedSplitter() {
        super(null,"res/splitter100-100.png");
    }

    public FixedSplitter(Point position){
        super(position, "res/splitter100-100.png");
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, null);
    }
    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
        this.outputA.recalculateFlow(currentFlow/2);
        this.outputB.recalculateFlow(currentFlow/2);
    }
}
