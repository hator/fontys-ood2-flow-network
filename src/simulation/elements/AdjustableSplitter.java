package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;

public class AdjustableSplitter extends Splitter implements java.io.Serializable{


    public AdjustableSplitter(Float splitRatio, Point position) {
        super(position);
        assert splitRatio != null;
        this.settings.splitRatio = splitRatio;
    }

    @Override
    void recalculateFlow() {
        settings.currentFlow = input.getFlow();
        outputA.recalculateFlow(settings.splitRatio * settings.currentFlow);
        outputB.recalculateFlow((1 - settings.splitRatio) * settings.currentFlow);
    }

    @Override
    void applySettings(Settings settings) {
        super.applySettings(settings);
        // TODO probably check if settings.splitRatio is not null
        this.settings.splitRatio = settings.splitRatio;
    }

    @Override
    public void render(Graphics graphics) {
        ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitter, graphics, getPosition());
    }
}
