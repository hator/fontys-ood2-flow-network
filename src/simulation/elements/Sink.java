package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sink extends Component implements java.io.Serializable {
    private Input input;

    public Sink(Settings settings, Point position) {
        super(settings, position);
        this.input = new Input(this, calculateSingleIOPosition(true));
    }

    Input getInput() {
        return input;
    }

    @Override
    void recalculateFlow() {
        this.settings.currentFlow = input.getFlow();
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
        input.render(graphics);
        renderFlow(graphics);
    }

    @Override
    public List<Input> getInputs() {
        return Collections.singletonList(input);
    }

    @Override
    public List<Output> getOutputs() {
        return Collections.emptyList();
    }
}
