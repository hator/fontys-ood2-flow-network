package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pump extends Component implements java.io.Serializable{
    private Output output = new Output(this);

    Pump(float currentFlow, float maxFlow) {
        super(null);
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
    }

    public Pump(float currentFlow, float maxFlow, Point position) {
        super(position);
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
    }

    Output getOutput() {
        return output;
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, null);
    }

    @Override
    void recalculateFlow() {
        this.getOutput().recalculateFlow(this.currentFlow);
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(output.pipeline);

        return pipelines;
    }

    @Override
    public void render(Graphics graphics) {
        ImageLibrary.drawImage(ImageLibrary.Images.Pump, graphics, getPosition());
    }
}
