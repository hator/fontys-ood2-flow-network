package simulation;

import util.Point;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public void applyTool(Point point, Tool tool) {}
}
