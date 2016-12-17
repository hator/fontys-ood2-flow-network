package simulation;

import util.Point;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public Result applyTool(Point point, Tool tool, Settings settings) {
        return Result.Success;
    }

    public Settings select(Point point) {
        return null; // FIXME
    }

    public Result remove(Point point) {
        return null; // FIXME
    }
}
