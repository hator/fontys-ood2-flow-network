package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementsFlowIntegrationTest {
    @Test
    public void twoComponentNetworkRecalculatesProperly() {
        /* Network: Pump ---Pipeline--> Sink */
        // Given
        float maxFlow = 10.f;
        float pumpFlow = 10.f;

        Pump pump = new Pump(pumpFlow, maxFlow);
        Sink sink = new Sink(maxFlow);
        Pipeline pipeline = new Pipeline(pump.getOutput(), sink.getInput(), maxFlow);

        // When
        sink.recalculateFlow();

        // Then
        assertEquals(pumpFlow, sink.getFlow(), 0.001f);
    }

}