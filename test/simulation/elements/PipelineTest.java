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

        Point testPoint1 = new Point(Pipeline.clickRadius, 13 + Pipeline.clickRadius);
        Point testPoint2 = new Point(-4 + Pipeline.clickRadius, -15 + Pipeline.clickRadius);
        Point testPoint3 = new Point(-3 + Pipeline.clickRadius, 60 + Pipeline.clickRadius);
        Point testPoint4 = new Point(-10 + Pipeline.clickRadius, -10 + Pipeline.clickRadius);
        Point testPoint5 = new Point(Pipeline.clickRadius, 15 + Pipeline.clickRadius);
        Point testPoint6 = new Point(5 + Pipeline.clickRadius, 35 + Pipeline.clickRadius);
        Point testPoint7 = new Point(-6 + Pipeline.clickRadius, 35 + Pipeline.clickRadius);
        Point testPoint8 = new Point(-14 + Pipeline.clickRadius, 35 + Pipeline.clickRadius);

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