package simulation;

import simulation.elements.Pump;
import util.Point;

import java.awt.*;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public Result applyTool(Point point, Tool tool, Settings settings) {
        switch(tool){
            case AddPump: flowNetwork.addComponent(new Pump(settings.currentFlow, settings.maxFlow, point));
            return Result.Success;
            default: return Result.Success; //TODO fix return result
        }

        //return Result.Success;
    }

    public Settings select(Point point) {
        return null; // FIXME
    }
    public void render(Graphics g){
        flowNetwork.render(g);
    }

    public Result remove(Point point) {
        return null; // FIXME
    }
}
