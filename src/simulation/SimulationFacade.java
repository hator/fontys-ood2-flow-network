package simulation;

import simulation.elements.*;
import simulation.elements.Component;
import util.Point;
import java.util.List;
import java.awt.*;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }


    //overload for pipelines because they need a list of points, not just one point.
    //
    public Result applyTool(List<Point> points, Settings settings)
    {
        Pipeline p = new Pipeline(new Output(flowNetwork.findComponent(points.get(0))), new Input(flowNetwork.findComponent(points.get(0))), settings.maxFlow, points);
        flowNetwork.addPipeline(p);
        return Result.Success;
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
        return flowNetwork.findElement(point).getSettings();
    }
    public void render(Graphics g){
        flowNetwork.render(g);
    }

    public Result remove(Point point) {
        return null;
    }
}
