package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Merger extends Component implements java.io.Serializable{
    private Input inputA = new Input(this);
    private Input inputB = new Input(this);
    private Output output = new Output(this);

    Merger() {
        super(null);
    }

    public Merger(Point position) {
        super(position);
    }

    Input getInputA() {
        return inputA;
    }

    Input getInputB() {
        return inputB;
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, null);
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = inputA.getFlow() + inputB.getFlow();
        this.output.recalculateFlow(this.currentFlow);
    }

    Output getOutput() {
        return output;
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(inputA.pipeline);
        pipelines.add(inputB.pipeline);
        pipelines.add(output.pipeline);

        return pipelines;
    }

    @Override
    public void render(Graphics graphics) {
        ImageLibrary.drawImage(ImageLibrary.Images.Merger, graphics, getPosition());
    }
}
