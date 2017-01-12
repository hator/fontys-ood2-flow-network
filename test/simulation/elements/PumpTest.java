package simulation.elements;

import org.junit.Test;
import simulation.Settings;
import util.Point;

import static org.junit.Assert.assertEquals;

public class PumpTest {
    @Test
    public void pumpReturnsSetFlow() {
        // Given
        float flow = 10.f;
        Pump pump = new Pump(new Settings(flow, flow, null), Point.zero());

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
        Pump pump = new Pump(new Settings(flow, flow, null), point);

        assertEquals(pump.inBoundingArea(new Point(99 + Component.CLICK_RADIUS, 100)), true);
        assertEquals(pump.inBoundingArea(new Point((int) (100 + 0.3 * Component.CLICK_RADIUS), (int) (100 + 0.3 * Component.CLICK_RADIUS))), true);
        assertEquals(pump.inBoundingArea(new Point(100, 101 + Component.CLICK_RADIUS)), false);
    }
}