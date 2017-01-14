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
        settings.setCurrentFlow(input.getFlow());
        outputA.recalculateFlow(settings.splitRatio * settings.getCurrentFlow());
        outputB.recalculateFlow((1 - settings.splitRatio) * settings.getCurrentFlow());
    }

    @Override
    public void render(Graphics graphics) {
        if(settings.getCurrentFlow() > settings.getMaxFlow()){
            ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitterState2, graphics, getPosition());
        } else if(settings.getCurrentFlow() == settings.getMaxFlow()){
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
