package simulation;

import simulation.elements.*;
import simulation.elements.Component;
import util.Point;

import java.awt.*;
import java.io.*;
import java.util.function.BiFunction;

public class SimulationFacade {
    private FlowNetwork flowNetwork = new FlowNetwork();
    private PipelineBuilder pipelineBuilder = new PipelineBuilder();
    private Element selectedElement = null;

    public void newFlowNetwork() {
        this.flowNetwork = new FlowNetwork();
    }

    public Result applyTool(Point point, Tool tool, Settings settings) {
        deselect();
        if (tool != Tool.AddPipeline) {
            pipelineBuilder = new PipelineBuilder(); // reset builder
        }

        switch(tool){
            case AddPump:{
                return withValidSettingsAddToNetwork(settings, point, Pump::new);
            }
            case AddMerger:{
                return withValidSettingsAddToNetwork(settings, point, Merger::new);
            }
            case AddSink:{
                return withValidSettingsAddToNetwork(settings, point, Sink::new);
            }
            case AddAdjustableSplitter:{
                return withValidSettingsAddToNetwork(settings, point, AdjustableSplitter::new);
            }
            case AddFixedSplitter:{
                return withValidSettingsAddToNetwork(settings, point, FixedSplitter::new);
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

    private Result withValidSettingsAddToNetwork(Settings settings, Point position,
                                                 BiFunction<Settings, Point, Component> constructor) {
        if (!settings.isValid()) {
            return Result.InvalidSettings;
        }
        Component c = constructor.apply(settings, position);
        return addToFlowNetwork(c);
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
        selectedElement = flowNetwork.findElement(point);
        if (selectedElement != null) {
            return selectedElement.getSettings();
        } else {
            return null;
        }
    }

    public void render(Graphics g){
        flowNetwork.render(g);
        pipelineBuilder.render(g);
        flowNetwork.renderLast(g);
    }

    public Result remove(Point point) {
        deselect();
        if(flowNetwork.findElement(point) != null){
            flowNetwork.removeElement(flowNetwork.findElement(point));
            return Result.Success;
        }
        return Result.Failure;
    }

    private void deselect() {
        selectedElement = null;
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

    public void settingsChanged() {
        if (selectedElement != null) {
            selectedElement.recalculateFlow();
        }
    }
}
