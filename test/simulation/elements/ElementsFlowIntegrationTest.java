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
        pump.recalculateFlow();

        // Then
        assertEquals(pumpFlow, sink.getFlow(), 0.001f);
    }

    @Test
    public void allComponentsRecalculateProperly() {
        /* Network: [flow]
                                        /--------------------b[5]------------------------> Merger[6] --f[6]--> SinkA[6]
            Pump[10] --a[10]--> FixedSplitter[10]                                /--d[1]--> /
                                        \--c[5]--> AdjustableSplitter(ratio=0.2)[5]
                                                                                \--e[4]--> SinkB[4]
        */
        // Given
        float maxFlow = 10.f;
        float pumpFlow = 10.f;

        Pump pump = new Pump(pumpFlow, maxFlow);
        FixedSplitter fixedSplitter = new FixedSplitter();
        AdjustableSplitter adjustableSplitter = new AdjustableSplitter(0.2f);
        Merger merger = new Merger();
        Sink sinkA = new Sink(maxFlow);
        Sink sinkB = new Sink(maxFlow);
        Pipeline a = new Pipeline(pump.getOutput(), fixedSplitter.getInput(), maxFlow);
        Pipeline b = new Pipeline(fixedSplitter.getOutputA(), merger.getInputA(), maxFlow);
        Pipeline c = new Pipeline(fixedSplitter.getOutputB(), adjustableSplitter.getInput(), maxFlow);
        Pipeline d = new Pipeline(adjustableSplitter.getOutputA(), merger.getInputB(), maxFlow);
        Pipeline e = new Pipeline(adjustableSplitter.getOutputB(), sinkB.getInput(), maxFlow);
        Pipeline f = new Pipeline(merger.getOutput(), sinkA.getInput(), maxFlow);

        // When
        pump.recalculateFlow();

        // Then
        assertEquals(6.f, sinkA.getFlow(), 0.001f);
        assertEquals(4.f, sinkB.getFlow(), 0.001f);

        assertEquals(6.f, merger.getFlow(), 0.001f);
        assertEquals(5.f, adjustableSplitter.getFlow(), 0.001f);
        assertEquals(10.f, fixedSplitter.getFlow(), 0.001f);

    }
}