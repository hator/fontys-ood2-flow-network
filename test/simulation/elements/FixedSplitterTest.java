package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedSplitterTest {
    @Test
    public void unconnectedFixedSplitterHas0Flow() {
        // Given
        FixedSplitter splitter = new FixedSplitter();

        // When
        splitter.recalculateFlow();

        // Then
        assertEquals(0.f, splitter.getFlow(), 0.001f);
    }

    @Test
    public void fixedSplitterHasFlowOfItsInput() {
        // Given
        float inputFlow = 5.f;
        FixedSplitter splitter = new FixedSplitter();
        Pipeline inputPipe = new MockFixedFlowPipeline(inputFlow, splitter.getInput());

        // When
        inputPipe.recalculateFlow();

        // Then
        assertEquals(inputFlow, splitter.getFlow(), 0.001f);
    }

    @Test
    public void fixedSplitterSetsOutputsAsHalfOfInput() throws Exception {
        // Given
        float inputFlow = 6.f;
        float outputFlow = inputFlow/2.f;
        FixedSplitter splitter = new FixedSplitter();
        Pipeline inputPipe = new MockFixedFlowPipeline(inputFlow, splitter.getInput());

        // When
        inputPipe.recalculateFlow();

        // Then
        assertEquals(outputFlow, splitter.getOutputA().getFlow(), 0.001f);
        assertEquals(outputFlow, splitter.getOutputB().getFlow(), 0.001f);
    }
}