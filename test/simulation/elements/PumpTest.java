package simulation.elements;

import com.sun.javafx.scene.paint.GradientUtils;
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

        assertEquals(pump.inBoundingArea(new Point(81 + Component.clickRadius, 92 + Component.clickRadius)), true);
        assertEquals(pump.inBoundingArea(new Point(70 + Component.clickRadius, 99 + Component.clickRadius)), true);
        assertEquals(pump.inBoundingArea(new Point(120 + Component.clickRadius, 90 + Component.clickRadius)), false);
    }
}