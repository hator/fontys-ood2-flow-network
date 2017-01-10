package simulation.elements;

import simulation.Settings;
import ui.ImageLibrary;
import util.Point;

import java.awt.*;

public class AdjustableSplitter extends Splitter implements java.io.Serializable{
    private Float splitRatio;

    AdjustableSplitter() {
        this(0.5f);
    }

    AdjustableSplitter(Float splitRatio) {
        super(null);
        if (splitRatio != null){
            this.splitRatio = splitRatio;
        }
        else
        {
            this.splitRatio = 0.5f;
        }
    }

    public AdjustableSplitter(Float splitRatio, Point position) {
        super(position);
        if (splitRatio != null){
            this.splitRatio = splitRatio;
        }
        else
        {
            this.splitRatio = 0.5f;
        }
    }

    @Override
    public Settings getSettings() {
        return new Settings(currentFlow, maxFlow, splitRatio);
    }

    @Override
    void recalculateFlow() {
        currentFlow = input.getFlow();
        outputA.recalculateFlow(splitRatio * currentFlow);
        outputB.recalculateFlow((1 - splitRatio) * currentFlow);
    }

    @Override
    void applySettings(Settings settings) {
        super.applySettings(settings);
        // TODO probably check if settings.splitRatio is not null
        this.splitRatio = settings.splitRatio;
    }

    @Override
    public void render(Graphics graphics) {
        ImageLibrary.drawImage(ImageLibrary.Images.AdjustableSplitter, graphics, getPosition());
    }
}
