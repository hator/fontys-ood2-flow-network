package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;

public class AdjustableSplitter extends Splitter implements java.io.Serializable{


    public AdjustableSplitter(Settings settings, Point position) {
        super(settings, position);
    }

    @Override
    public void recalculateFlow() {
        settings.currentFlow = input.getFlow();
        outputA.recalculateFlow(settings.splitRatio * settings.currentFlow);
        outputB.recalculateFlow((1 - settings.splitRatio) * settings.currentFlow);
    }

    @Override
    public void render(Graphics graphics) {
        if(settings.currentFlow > settings.maxFlow){
            ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitterState2, graphics, getPosition());
        } else if(settings.currentFlow == settings.maxFlow){
            ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitterState0, graphics, getPosition());
        } else {
            ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitterState1, graphics, getPosition());
        }
    }
    @Override
    public void renderInputsAndOutputs(Graphics graphics) {
        input.render(graphics);
        outputA.render(graphics);
        outputB.render(graphics);
    }
}
