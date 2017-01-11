package simulation;

import simulation.elements.*;
import simulation.elements.Component;
import util.Point;

import java.awt.*;
import java.io.*;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();
    private PipelineBuilder pipelineBuilder = new PipelineBuilder();

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public Result applyTool(Point point, Tool tool, Settings settings) {
        if (tool != Tool.AddPipeline) {
            pipelineBuilder = new PipelineBuilder(); // reset builder
        }

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
            case AddPipeline: {
                if (!pipelineBuilder.hasOutput()) {
                    // First point of the pipeline has to be an output
                    Output output = flowNetwork.findOutput(point);
                    // TODO check if output is not already connected
                    if (output != null) {
                        pipelineBuilder.addPoint(point);
                        pipelineBuilder.setOutput(output);
                    } else {
                        return Result.Failure;
                    }
                } else {
                    pipelineBuilder.addPoint(point);

                    Input input = flowNetwork.findInput(point);
                    // TODO check if output is not already connected
                    if (input != null) {
                        pipelineBuilder.setInput(input);
                        pipelineBuilder.setSettings(settings);

                        Pipeline pipeline = pipelineBuilder.build();
                        flowNetwork.addPipeline(pipeline);
                        pipelineBuilder = new PipelineBuilder();
                    }
                }
                return Result.Success;
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
        pipelineBuilder.render(g);
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
