package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;

public class FixedSplitter extends Splitter implements java.io.Serializable {

    public FixedSplitter(Settings settings, Point position) {
        super(settings, position);
    }

    @Override
    public void recalculateFlow() {
        this.settings.currentFlow = input.getFlow();
        this.outputA.recalculateFlow(settings.currentFlow/2);
        this.outputB.recalculateFlow(settings.currentFlow/2);
    }

    @Override
    public void render(Graphics graphics) {
        if(settings.currentFlow > settings.maxFlow){
            ImageLibrary.drawImage(ImageLibrary.Images.SplitterState2, graphics, getPosition());
        } else if(settings.currentFlow == settings.maxFlow){
            ImageLibrary.drawImage(ImageLibrary.Images.SplitterState0, graphics, getPosition());
        } else {
            ImageLibrary.drawImage(ImageLibrary.Images.SplitterState1, graphics, getPosition());
        }
        input.render(graphics);
        outputA.render(graphics);
        outputB.render(graphics);
    }
}
