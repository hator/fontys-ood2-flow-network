package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Pump extends Component implements java.io.Serializable {
    private Output output;

    public Pump(Settings settings, Point position) {
        super(settings, position);
        this.output = new Output(this, calculateSingleIOPosition(false));
    }

    Output getOutput() {
        return output;
    }

    @Override
    public void recalculateFlow() {
        this.getOutput().recalculateFlow(this.settings.getCurrentFlow());
    }

    @Override
    public List<Pipeline> getPipelines() {
        return Collections.singletonList(output.pipeline);
    }

    @Override
    public void render(Graphics graphics) {
        if(settings.getCurrentFlow() > settings.getMaxFlow()){
            ImageLibrary.drawImage(ImageLibrary.Images.PumpState2, graphics, getPosition());
        } else if(settings.getCurrentFlow() == settings.getMaxFlow()){
            ImageLibrary.drawImage(ImageLibrary.Images.PumpState0, graphics, getPosition());
        } else {
            ImageLibrary.drawImage(ImageLibrary.Images.PumpState1, graphics, getPosition());
        }

    }
    @Override
    public void renderInputsAndOutputs(Graphics graphics) {
        output.render(graphics);
        renderFlow(graphics);
    }

    @Override
    public List<Input> getInputs() {
        return Collections.emptyList();
    }

    @Override
    public List<Output> getOutputs() {
        return Collections.singletonList(output);
    }
}
