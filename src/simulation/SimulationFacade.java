package simulation;

import simulation.elements.*;
import simulation.elements.Component;
import util.Point;

import java.awt.*;
import java.io.*;
import java.util.List;

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
        System.out.println(settings.currentFlow + " " + settings.maxFlow + " " + settings.splitRatio); //TODO: Current set settings do net get applied here
        switch(tool){
            case AddPump:{
                if (!settings.isValid()) return Result.InvalidSettings;
                Pump p = new Pump(settings.currentFlow, settings.maxFlow, point);
                return addToFlowNetwork(p);
            }
            case AddMerger:{
                if (!settings.isValid()) return Result.InvalidSettings;
                Merger c = new Merger(point);
                return addToFlowNetwork(c);
            }
            case AddSink:{
                if (!settings.isValid()) return Result.InvalidSettings;
                Sink c = new Sink(settings.maxFlow, point);
                return addToFlowNetwork(c);
            }
            case AddAdjustableSplitter:{
                if (!settings.isValid()) return Result.InvalidSettings;
                AdjustableSplitter c = new AdjustableSplitter(settings.splitRatio, point);
                return addToFlowNetwork(c);
            }
            case AddFixedSplitter:{
                if (!settings.isValid()) return Result.InvalidSettings;
                FixedSplitter c = new FixedSplitter(point);
                return addToFlowNetwork(c);
            }
            default: return Result.Failure; //TODO fix return result
        }
    }

    private Result addToFlowNetwork(Component p) {
        if (!flowNetwork.isOverlapping(p)) {
            flowNetwork.addComponent(p);
            return Result.Success;
        } else {
            return Result.ComponentsOverlapping;
        }
    }

    public Settings select(Point point) {
        Element elem = flowNetwork.findElement(point);
        if (elem != null) {
            return elem.getSettings();
        } else {
            return null;
        }
    }

    public void render(Graphics g){
        flowNetwork.render(g);
    }

    public Result remove(Point point) {
        if(flowNetwork.findElement(point) != null){
            flowNetwork.removeElement(flowNetwork.findElement(point));
            return Result.Success;
        }
        return Result.Failure;
    }

    public void saveFlowNetwork(OutputStream out) throws IOException {
        try (ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            objOut.writeObject(flowNetwork);
        }
    }

    public void loadFlowNetwork(InputStream in) throws IOException {
        try (ObjectInputStream objIn = new ObjectInputStream(in)) {

            this.flowNetwork = (FlowNetwork) objIn.readObject();

        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
