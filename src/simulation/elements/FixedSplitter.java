package simulation.elements;

import simulation.Settings;
import util.Point;

public class FixedSplitter extends Splitter implements java.io.Serializable{

    FixedSplitter() {
        super(null);
    }

    public FixedSplitter(Point position){
        super(position);
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
