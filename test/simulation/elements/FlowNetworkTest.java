package simulation.elements;

import org.junit.Test;
import simulation.FlowNetwork;
import simulation.Settings;
import util.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FlowNetworkTest {

    @Test
    public void removeElementTest() {
        FlowNetwork network = new FlowNetwork();
        Settings pumpSettings = new Settings(10, 10, null);
        Settings pumpSettings2 = new Settings(10, 10, null);
        Settings sinkSettings = new Settings(0, 20, null);

        Pump pump = new Pump(pumpSettings, Point.zero());
        Pump pump2 = new Pump(pumpSettings2, Point.zero());
        Sink sink = new Sink(sinkSettings, Point.zero());
        Merger merger = new Merger(Settings.getDefault(), Point.zero());
        Pipeline pipeline1 = new Pipeline(pump.getOutput(), merger.getInputA(), Collections.emptyList(), new Settings(0, 10, null));
        Pipeline pipeline2 = new Pipeline(pump2.getOutput(), merger.getInputB(), Collections.emptyList(), new Settings(0, 10, null));
        Pipeline pipeline3 = new Pipeline(merger.getOutput(), sink.getInput(), Collections.emptyList(), new Settings(0, 20, null));

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

        Pipeline newPipe = new Pipeline(pump.getOutput(), merger.getInputA(), Collections.emptyList(), new Settings(0, 10, null));
        pump.recalculateFlow();

        network.removeElement(pump);

        assertEquals(null, merger.getInputA().pipeline);
        assertEquals(10f, sink.getFlow(), 0.001f);
    }

    @Test
    public void findElementTest() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(new Settings(10, 10, null), new Point(100, 100));
        Sink sink = new Sink(new Settings(0, 20, null), new Point(200, 200));
        Point point1 = new Point(110, 110);
        Point point2 = new Point(190, 110);
        Point point3 = new Point(190, 190);

        List<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);

        Pipeline pipeline1 = new Pipeline(pump.getOutput(), sink.getInput(), points, new Settings(0, 10, null));

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

        Pump pump = new Pump(Settings.getDefault(), new Point(100, 100));
        Merger merger = new Merger(Settings.getDefault(), new Point(300 + Component.CLICK_RADIUS, 300 + Component.CLICK_RADIUS));
        Sink sink = new Sink(Settings.getDefault(), new Point(170 + Component.CLICK_RADIUS, 170 + Component.CLICK_RADIUS));
        Pump pump1 = new Pump(Settings.getDefault(), new Point(99 + Component.CLICK_RADIUS, 100));

        network.addComponent(pump);

        assertTrue(network.isOverlapping(pump1));
        assertFalse(network.isOverlapping(merger));
        assertFalse(network.isOverlapping(sink));
    }

    @Test
    public void addingPipelineRecalculatesFlow() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(new Settings(10, 10, null), Point.zero());
        Pump pump2 = new Pump(new Settings(10, 10, null), Point.zero());
        Sink sink = new Sink(new Settings(0, 20, null), Point.zero());
        Merger merger = new Merger(Settings.getDefault(), Point.zero());
        Pipeline pipeline1 = createPipeline(pump.getOutput(), merger.getInputA(), 10);
        Pipeline pipeline2 = createPipeline(pump2.getOutput(), merger.getInputB(), 10);
        Pipeline pipeline3 = createPipeline(merger.getOutput(), sink.getInput(), 20);

        network.addComponent(pump);
        network.addComponent(pump2);
        network.addComponent(sink);
        network.addComponent(merger);

        network.addPipeline(pipeline1);
        network.addPipeline(pipeline2);
        network.addPipeline(pipeline3);

        assertEquals(20f, sink.getFlow(), 0.001f);
    }

    @Test
    public void findInputFindsInputInsideComponent() throws Exception {
        // Given
        FlowNetwork network = new FlowNetwork();

        Point mergerPosition = new Point(100, 100);
        Merger merger = new Merger(Settings.getDefault(), mergerPosition);

        network.addComponent(merger);

        // When

        // Merger radius: 50, input radius: 10
        Point clickOutsideMerger = new Point(20, 20);
        Input notFound = network.findInput(clickOutsideMerger);

        Point clickOnMergerButOutsideInput = new Point(80, 100);
        Input alsoNotFound = network.findInput(clickOnMergerButOutsideInput);

        Point clickOnInputPosition = new Point(75, 80);
        Input found = network.findInput(clickOnInputPosition);

        // Then
        assertEquals(null, notFound);
        assertEquals(null, alsoNotFound);
        assertEquals(merger.getInputA(), found);
    }

    @Test
    public void findOutputFindsOutputInsideComponent() throws Exception {
        // Given
        FlowNetwork network = new FlowNetwork();

        Point splitterPosition = new Point(100, 100);
        FixedSplitter splitter = new FixedSplitter(Settings.getDefault(), splitterPosition);

        network.addComponent(splitter);

        // When

        // Merger radius: 50, input radius: 10
        Point clickOutsideMerger = new Point(20, 20);
        Output notFound = network.findOutput(clickOutsideMerger);

        Point clickOnMergerButOutsideOutput = new Point(80, 100);
        Output alsoNotFound = network.findOutput(clickOnMergerButOutsideOutput);

        Point clickOnOutputPosition = new Point(122, 80);
        Output found = network.findOutput(clickOnOutputPosition);

        // Then
        assertEquals(null, notFound);
        assertEquals(null, alsoNotFound);
        assertEquals(splitter.getOutputA(), found);
    }

    private Pipeline createPipeline(Output output, Input input, float maxFlow) {
        return new Pipeline(output, input, Collections.emptyList(), new Settings(0, maxFlow, null));
    }
}
