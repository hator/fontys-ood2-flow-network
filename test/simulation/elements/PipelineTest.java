package simulation.elements;

import org.junit.Test;
import util.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PipelineTest {
    @Test
    public void pipelineHasFlowOfOutput() {
        // Given
        float flow = 10.f;
        Output mockOutput = new MockOutput(flow);
        Input emptyInput = new MockInput();
        Pipeline pipeline = new Pipeline(mockOutput, emptyInput, flow);

        // When
        pipeline.recalculateFlow();

        // Then
        assertEquals(flow, pipeline.getFlow(), 0.001f);
    }

    @Test
    public void pipelineContainsPoint() {
        float flow = 10.f;
        Output mockOutput = new MockOutput(flow);
        Input emptyInput = new MockInput();

        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(10, 10));
        pointList.add(new Point(30, 10));
        pointList.add(new Point(30, 40));
        pointList.add(new Point(10, 40));

        Pipeline pipeline = new Pipeline(mockOutput, emptyInput, flow, pointList);

        Point testPoint1 = new Point(Pipeline.CLICK_RADIUS, 13 + Pipeline.CLICK_RADIUS);
        Point testPoint2 = new Point(-4 + Pipeline.CLICK_RADIUS, -15 + Pipeline.CLICK_RADIUS);
        Point testPoint3 = new Point(-3 + Pipeline.CLICK_RADIUS, 60 + Pipeline.CLICK_RADIUS);
        Point testPoint4 = new Point(-10 + Pipeline.CLICK_RADIUS, -10 + Pipeline.CLICK_RADIUS);
        Point testPoint5 = new Point(Pipeline.CLICK_RADIUS, 15 + Pipeline.CLICK_RADIUS);
        Point testPoint6 = new Point(5 + Pipeline.CLICK_RADIUS, 35 + Pipeline.CLICK_RADIUS);
        Point testPoint7 = new Point(-6 + Pipeline.CLICK_RADIUS, 35 + Pipeline.CLICK_RADIUS);
        Point testPoint8 = new Point(-14 + Pipeline.CLICK_RADIUS, 35 + Pipeline.CLICK_RADIUS);

        assertEquals(pipeline.inBoundingArea(testPoint1), true);
        assertEquals(pipeline.inBoundingArea(testPoint2), true);
        assertEquals(pipeline.inBoundingArea(testPoint3), false);
        assertEquals(pipeline.inBoundingArea(testPoint4), true);
        assertEquals(pipeline.inBoundingArea(testPoint5), true);
        assertEquals(pipeline.inBoundingArea(testPoint6), true);
        assertEquals(pipeline.inBoundingArea(testPoint7), false);
        assertEquals(pipeline.inBoundingArea(testPoint8), false);
    }
}