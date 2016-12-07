package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementsRecalculationTest {
    @Test
    public void pumpReturnsSetFlow() {
        // Given
        float flow = 10.f;

        // When
        Pump pump = new Pump(flow, flow);

        // Then
        assertEquals(flow, pump.getFlow(), 0.001f);
        assertEquals(flow, pump.getOutput().getFlow(), 0.001f);
    }

    @Test
    public void pipelineReturnsCalculatedFlow() {
        // Given
        float flow = 10.f;
        Pump pump = new Pump(flow, flow);
        Input emptyInput = new Input(null);

        // When
        Pipeline pipeline = new Pipeline(pump.getOutput(), emptyInput, flow);

        // Then
        assertEquals(flow, pipeline.getFlow(), 0.001f);
    }

    @Test
    public void twoComponentNetworkRecalculatesProperely() {
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