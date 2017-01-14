package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Merger extends Component implements java.io.Serializable {
    private Input inputA;
    private Input inputB;
    private Output output;

    public Merger(Settings settings, Point position) {
        super(settings, position);
        inputA = new Input(this, calculateOneOfTwoIOsPosition(true, 1));
        inputB = new Input(this, calculateOneOfTwoIOsPosition(true, 2));
        output = new Output(this, calculateSingleIOPosition(false));
    }

    Input getInputA() {
        return inputA;
    }

    Input getInputB() {
        return inputB;
    }

    @Override
    public void recalculateFlow() {
        this.settings.setCurrentFlow(inputA.getFlow() + inputB.getFlow());
        this.output.recalculateFlow(this.settings.getCurrentFlow());
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
        if(settings.getCurrentFlow() > settings.getMaxFlow()){
            ImageLibrary.drawImage(ImageLibrary.Images.MergerState2, graphics, getPosition());
        } else if(settings.getCurrentFlow() == settings.getMaxFlow()){
            ImageLibrary.drawImage(ImageLibrary.Images.MergerState0, graphics, getPosition());
        } else {
            ImageLibrary.drawImage(ImageLibrary.Images.MergerState1, graphics, getPosition());
        }
    }

    public void renderInputsAndOutputs(Graphics graphics){
        inputA.render(graphics);
        inputB.render(graphics);
        output.render(graphics);
    }

    @Override
    public List<Input> getInputs() {
        List<Input> inputs = new ArrayList<>(2);
        inputs.add(inputA);
        inputs.add(inputB);
        return inputs;
    }

    @Override
    public List<Output> getOutputs() {
        return Collections.singletonList(output);
    }
}
