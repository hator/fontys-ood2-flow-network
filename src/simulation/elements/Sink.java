package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sink extends Component implements java.io.Serializable {
    private Input input = new Input(this);

    Sink(float maxFlow) {
        super(null);
        this.maxFlow = maxFlow;
    }

    public Sink(float maxFlow, Point position) {
        super(position);
        this.maxFlow = maxFlow;
    }

    Input getInput() {
        return input;
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, null);
    }
    @Override
    void recalculateFlow() {
        this.currentFlow = input.getFlow();
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(input.pipeline);

        return pipelines;
    }

    @Override
    public void render(Graphics graphics) {
        ImageLibrary.drawImage(ImageLibrary.Images.Barrel, graphics, getPosition());
    }
}
