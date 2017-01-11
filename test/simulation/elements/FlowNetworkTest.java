package simulation.elements;

import org.junit.Test;
import simulation.FlowNetwork;
import util.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlowNetworkTest {

    @Test
    public void removeElementTest() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(10, 10, Point.zero());
        Pump pump2 = new Pump(10, 10, Point.zero());
        Sink sink = new Sink(20, Point.zero());
        Merger merger = new Merger(Point.zero());
        Pipeline pipeline1 = new Pipeline(pump.getOutput(), merger.getInputA(), 10);
        Pipeline pipeline2 = new Pipeline(pump2.getOutput(), merger.getInputB(), 10);
        Pipeline pipeline3 = new Pipeline(merger.getOutput(), sink.getInput(), 20);

        network.addComponent(pump);
        network.addComponent(pump2);
        network.addComponent(sink);
        network.addComponent(merger);

        network.addPipeline(pipeline1);
        network.addPipeline(pipeline2);
        network.addPipeline(pipeline3);

        pump.recalculateFlow();
        pump2.recalculateFlow();

        network.removeElement(pipeline1);

        assertEquals(merger.getInputA().pipeline, null);
        assertEquals(pump.getOutput().pipeline, null);
        assertEquals(10f, sink.getFlow(), 0.001f);

        Pipeline newPipe = new Pipeline(pump.getOutput(), merger.getInputA(), 10);
        pump.recalculateFlow();

        network.removeElement(pump);

        assertEquals(merger.getInputA().pipeline, null);
        assertEquals(10f, sink.getFlow(), 0.001f);
    }

    @Test
    public void findElementTest() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(10, 10, new Point(100, 100));
        Sink sink = new Sink(10, new Point(200, 200));
        Point point1 = new Point(110, 110);
        Point point2 = new Point(190, 110);
        Point point3 = new Point(190, 190);

        List<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);

        Pipeline pipeline1 = new Pipeline(pump.getOutput(), sink.getInput(), 10, points);

        network.addComponent(pump);
        network.addComponent(sink);
        network.addPipeline(pipeline1);

        Point clickPoint2 = new Point(200 + Component.CLICK_RADIUS, 200 + Component.CLICK_RADIUS);
        Point clickPoint3 = new Point(100, 101 - Component.CLICK_RADIUS);

        assertTrue(network.findElement(clickPoint2) == null);
        assertTrue(network.findElement(clickPoint3) instanceof Pump);
    }

    @Test
    public void isOverlappingTest() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(10, 10, new Point(100, 100));
        Merger merger = new Merger(new Point(300 + Component.CLICK_RADIUS, 300 + Component.CLICK_RADIUS));
        Sink sink = new Sink(10, new Point(170 + Component.CLICK_RADIUS, 170 + Component.CLICK_RADIUS));
        Pump pump1 = new Pump(10, 10, new Point(99 + Component.CLICK_RADIUS, 100));

        network.addComponent(pump);

        assertTrue(network.isOverlapping(pump1));
        assertFalse(network.isOverlapping(merger));
        assertFalse(network.isOverlapping(sink));
    }

    @Test
    public void addingPipelineRecalculatesFlow() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(10, 10, Point.zero());
        Pump pump2 = new Pump(10, 10, Point.zero());
        Sink sink = new Sink(20, Point.zero());
        Merger merger = new Merger(Point.zero());
        Pipeline pipeline1 = new Pipeline(pump.getOutput(), merger.getInputA(), 10);
        Pipeline pipeline2 = new Pipeline(pump2.getOutput(), merger.getInputB(), 10);
        Pipeline pipeline3 = new Pipeline(merger.getOutput(), sink.getInput(), 20);

        network.addComponent(pump);
        network.addComponent(pump2);
        network.addComponent(sink);
        network.addComponent(merger);

        network.addPipeline(pipeline1);
        network.addPipeline(pipeline2);
        network.addPipeline(pipeline3);

        assertEquals(20f, sink.getFlow(), 0.001f);
    }
}
