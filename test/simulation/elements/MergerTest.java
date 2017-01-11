package simulation.elements;

import org.junit.Test;
import util.Point;

import static org.junit.Assert.assertEquals;

public class MergerTest {
    @Test
    public void unconnectedMergerHas0Flow() {
        // Given
        Merger merger = new Merger(Point.zero());

        // When
        merger.recalculateFlow();

        // Then
        assertEquals(0.f, merger.getFlow(), 0.001f);
    }

    @Test
    public void mergerHasFlowOfSumOfItsInputs() {
        // Given
        float flowA = 1.f;
        float flowB = 2.f;
        float expectedFlow = flowA + flowB;
        Merger merger = new Merger(Point.zero());
        Pipeline pipeA = new MockFixedFlowPipeline(flowA, merger.getInputA());
        Pipeline pipeB = new MockFixedFlowPipeline(flowB, merger.getInputB());

        // When
        pipeA.recalculateFlow();
        pipeB.recalculateFlow();

        // Then
        assertEquals(expectedFlow, merger.getFlow(), 0.001f);
    }
}