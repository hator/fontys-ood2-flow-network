package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pump extends Component implements java.io.Serializable{
    private Output output = new Output(this);


    public Pump(float currentFlow, float maxFlow, Point position) {
        super(position);
        this.settings.currentFlow = currentFlow;
        this.settings.maxFlow = maxFlow;
    }

    Output getOutput() {
        return output;
    }

    @Override
    void recalculateFlow() {
        this.getOutput().recalculateFlow(this.settings.currentFlow);
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
