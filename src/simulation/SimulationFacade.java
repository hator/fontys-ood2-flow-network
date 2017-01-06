package simulation;

import simulation.elements.*;
import util.Point;

import java.awt.*;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public Result applyTool(Point point, Tool tool, Settings settings) {
        switch(tool){
            case AddPump:{
                Pump p = new Pump(settings.currentFlow, settings.maxFlow, point);
                if(!flowNetwork.isOverlapping(p)){
                    flowNetwork.addComponent(p);
                    return Result.Success;
                } else {
                    return Result.ComponentsOverlapping;
                }
            }
            case AddMerger:{
                Merger c = new Merger(point);
                if(!flowNetwork.isOverlapping(c)){
                    flowNetwork.addComponent(c);
                    return Result.Success;
                } else {
                    return Result.ComponentsOverlapping;
                }
            }
            case AddSink:{
                Sink c = new Sink(settings.maxFlow, point);
                if(!flowNetwork.isOverlapping(c)){
                    flowNetwork.addComponent(c);
                    return Result.Success;
                } else {
                    return Result.ComponentsOverlapping;
                }
            }
            case AddAdjustableSplitter:{
                AdjustableSplitter c = new AdjustableSplitter(0.5f, point); //TODO fix split ratio - settings.splitratio is not passed down correctly
                if(!flowNetwork.isOverlapping(c)){
                    flowNetwork.addComponent(c);
                    return Result.Success;
                } else {
                    return Result.ComponentsOverlapping;
                }
            }
            case AddFixedSplitter:{
                FixedSplitter c = new FixedSplitter(point);
                if(!flowNetwork.isOverlapping(c)){
                    flowNetwork.addComponent(c);
                    return Result.Success;
                } else {
                    return Result.ComponentsOverlapping;
                }
            }
            default: return Result.Success; //TODO fix return result
        }
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
