package simulation.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PipelineTest {
    @Test
    public void pipelineHasFlowOfOutput() {
        // Given
        float flow = 10.f;
        Output mockOutput = new MockOutput(flow);
        Input emptyInput = new MockInput();
        Pipeline pipeline = new Pipeline(mockOutput, emptyInput, flow);

        // When
        pipeline.recalculateFlow();

        // Then
        assertEquals(flow, pipeline.getFlow(), 0.001f);
    }
}