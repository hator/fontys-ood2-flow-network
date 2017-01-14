package simulation.elements;

import simulation.Settings;
import util.Point;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class Pipeline extends Element implements java.io.Serializable {
    static final int CLICK_RADIUS = 5;
    private static final Point FLOW_LABEL_OFFSET = new Point(40, 20);
    private List<Point> points;
    private Input input;
    private Output output;

    public Pipeline(Output output, Input input, List<Point> points, Settings settings) {
        super(settings);
        assert input != null;
        assert output != null;
        assert points != null;

        this.output = output;
        this.input = input;
        this.points = points;

        input.attachPipeline(this);
        output.attachPipeline(this);
    }

    @Override
    public void recalculateFlow() {
        this.settings.setCurrentFlow(output.getFlow());
        input.recalculateFlow(this.settings.getCurrentFlow());
    }

    @Override
    public boolean inBoundingArea(Point point) {
        assert point != null;

        for (int i = 0; i < points.size() - 1; i++) {
            Point segmentA = points.get(i);
            Point segmentB = points.get(i + 1);

            if (point.inBoundingSegment(segmentA, segmentB, CLICK_RADIUS)) {
                return true;
            }
        }
        return false;
    }

    public void detach() {
        input.detachPipeline();

        if (!input.isComponentBeingDeleted()) {
            input.recalculateFlow(0);
        }

        output.detachPipeline();
    }

    public void attach() {
        output.component.recalculateFlow();
    }


    public void render(Graphics g) {
        Point a, b;
        for (int i = 1; i < points.size(); i++) {
            a = points.get(i - 1);
            b = points.get(i);

            g.setColor(Color.black); // Drawing the line outline
            ((Graphics2D)g).setStroke(new BasicStroke(10));
            ((Graphics2D)g).draw(new Line2D.Float(a.x, a.y, b.x, b.y));

        }
        for(int i = 1; i < points.size(); i++){
            a = points.get(i - 1);
            b = points.get(i);
            if(settings.getCurrentFlow() > settings.getMaxFlow()){
                g.setColor(new Color(134,55,29)); //red
            } else if(settings.getCurrentFlow() == settings.getMaxFlow()){
                g.setColor(new Color(40,190,63)); //dark green
            } else {
                g.setColor(new Color(124,255,0)); //light green
            }
            ((Graphics2D)g).setStroke(new BasicStroke(5));
            ((Graphics2D)g).draw(new Line2D.Float(a.x, a.y, b.x, b.y));
        }
        renderFlow(g);
    }

    private void renderFlow(Graphics g) {
        Point middlePoint = getMiddlePoint();

        if (middlePoint != null) {
            Point flowLabelPosition = getMiddlePoint().plus(FLOW_LABEL_OFFSET);
            renderFlowAtPosition(g, settings, flowLabelPosition);
        }
    }

    private Point getMiddlePoint() {
        try {
            return points.get((points.size() - 1)/2);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
