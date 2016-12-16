package simulation.elements;

import org.junit.Test;
import util.Point;

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

    @Test
    public void pumpInBoundingRadius(){
        float flow = 10.f;
        Point point = new Point(100, 100);
        Pump pump = new Pump(flow, flow, point);

        assertEquals(pump.inBoundingArea(new Point(81 + Component.CLICK_RADIUS, 92 + Component.CLICK_RADIUS)), true);
        assertEquals(pump.inBoundingArea(new Point(70 + Component.CLICK_RADIUS, 99 + Component.CLICK_RADIUS)), true);
        assertEquals(pump.inBoundingArea(new Point(120 + Component.CLICK_RADIUS, 90 + Component.CLICK_RADIUS)), false);
    }
}