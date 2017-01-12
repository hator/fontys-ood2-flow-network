package simulation.elements;

import org.junit.Test;
import simulation.Settings;
import util.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PipelineTest {
    @Test
    public void pipelineHasFlowOfOutput() {
        // Given
        float flow = 10.f;
        Output mockOutput = new MockOutput(flow);
        Input emptyInput = new MockInput();
        Pipeline pipeline = new Pipeline(mockOutput, emptyInput, Collections.emptyList(), new Settings(0, flow, null));

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

        Pipeline pipeline = new Pipeline(mockOutput, emptyInput, pointList, new Settings(0, flow, null));

        Point testPoint1 = new Point(5, 5);
        Point testPoint2 = new Point(20, 9 + Pipeline.CLICK_RADIUS);
        Point testPoint3 = new Point(20, 11 + Pipeline.CLICK_RADIUS);
        Point testPoint4 = new Point(30 + Pipeline.CLICK_RADIUS, 30);
        Point testPoint5 = new Point(31 + Pipeline.CLICK_RADIUS, 30);
        Point testPoint6 = new Point(29 + Pipeline.CLICK_RADIUS, 41);

        assertEquals(pipeline.inBoundingArea(testPoint1), true);
        assertEquals(pipeline.inBoundingArea(testPoint2), true);
        assertEquals(pipeline.inBoundingArea(testPoint3), false);
        assertEquals(pipeline.inBoundingArea(testPoint4), true);
        assertEquals(pipeline.inBoundingArea(testPoint5), false);
        assertEquals(pipeline.inBoundingArea(testPoint6), false);
    }
}