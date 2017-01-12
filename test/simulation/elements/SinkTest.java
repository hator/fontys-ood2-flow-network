package simulation.elements;

import org.junit.Test;
import simulation.Settings;
import util.Point;

import static org.junit.Assert.assertEquals;

public class SinkTest {
    @Test
    public void unconnectedSinkHas0Flow() {
        // Given
        Sink sink = new Sink(new Settings(0, 10.f, null), Point.zero());

        // When
        sink.recalculateFlow();

        // Then
        assertEquals(0.f, sink.getFlow(), 0.001f);

    }

    @Test
    public void sinkHasFlowOfItsInput() {
        // Given
        float flow = 5.f;
        float maxFlow = 10.f;
        Sink sink = new Sink(new Settings(0, maxFlow, null), Point.zero());
        Pipeline pipe = new MockFixedFlowPipeline(flow, sink.getInput());

        // When
        pipe.recalculateFlow();

        // Then
        assertEquals(flow, sink.getFlow(), 0.001f);
    }
}