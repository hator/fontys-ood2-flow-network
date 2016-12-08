package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinkTest {
    @Test
    public void unconnectedSinkHas0Flow() {
        // Given
        Sink sink = new Sink(10.f);

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
        Sink sink = new Sink(maxFlow);
        Pipeline pipe = new MockFixedFlowPipeline(flow, sink.getInput());

        // When
        sink.recalculateFlow();

        // Then
        assertEquals(flow, sink.getFlow(), 0.001f);
    }
}