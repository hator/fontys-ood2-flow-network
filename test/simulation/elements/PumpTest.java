package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PumpTest {
    @Test
    public void pumpReturnsSetFlow() {
        // Given
        float flow = 10.f;
        Pump pump = new Pump(flow, flow);

        // When

        pump.recalculateFlow();

        // Then
        assertEquals(flow, pump.getFlow(), 0.001f);
        assertEquals(flow, pump.getOutput().getFlow(), 0.001f);
    }
}