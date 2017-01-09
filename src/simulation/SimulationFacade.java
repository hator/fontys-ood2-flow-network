package simulation;

import simulation.elements.*;
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
                //settings = new Settings(5.0f,118.0f,null);
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
                AdjustableSplitter c = new AdjustableSplitter(settings.splitRatio, point);
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
            case Save:{
                saveFlowNetwork("res/savedNetwork.ser");
            }
            case Load:{
                loadFlowNetwork("res/savedNetwork.ser");
            }
            default: return Result.Failure; //TODO fix return result
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

    private void saveFlowNetwork(String path){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(flowNetwork);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    private void loadFlowNetwork(String path){
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            FlowNetwork network = (FlowNetwork) in.readObject();
            in.close();
            fileIn.close();
            this.flowNetwork = network;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
