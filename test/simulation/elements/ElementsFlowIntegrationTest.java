package simulation.elements;

import org.junit.Test;
import simulation.Settings;
import util.Point;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ElementsFlowIntegrationTest {
    @Test
    public void twoComponentNetworkRecalculatesProperly() {
        /* Network: Pump ---Pipeline--> Sink */
        // Given
        float maxFlow = 10.f;
        float pumpFlow = 10.f;
        Settings pumpSettings = new Settings(pumpFlow, maxFlow, null);
        Settings sinkSettings = new Settings(0, maxFlow, null);

        Pump pump = new Pump(pumpSettings, Point.zero());
        Sink sink = new Sink(sinkSettings, Point.zero());
        Pipeline pipeline = new Pipeline(pump.getOutput(), sink.getInput(), Collections.emptyList(), new Settings(0, maxFlow, null));

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
        Settings pumpSettings = new Settings(pumpFlow, maxFlow, null);
        Settings sinkASettings = new Settings(0, maxFlow, null);
        Settings sinkBSettings = new Settings(0, maxFlow, null);

        Pump pump = new Pump(pumpSettings, Point.zero());
        FixedSplitter fixedSplitter = new FixedSplitter(new Settings(0, 10, null), Point.zero());
        AdjustableSplitter adjustableSplitter = new AdjustableSplitter(new Settings(0, 10, 0.2f), Point.zero());
        Merger merger = new Merger(Settings.getDefault(), Point.zero());
        Sink sinkA = new Sink(sinkASettings, Point.zero());
        Sink sinkB = new Sink(sinkBSettings, Point.zero());
        Pipeline a = createPipeline(pump.getOutput(), fixedSplitter.getInput(), maxFlow);
        Pipeline b = createPipeline(fixedSplitter.getOutputA(), merger.getInputA(), maxFlow);
        Pipeline c = createPipeline(fixedSplitter.getOutputB(), adjustableSplitter.getInput(), maxFlow);
        Pipeline d = createPipeline(adjustableSplitter.getOutputA(), merger.getInputB(), maxFlow);
        Pipeline e = createPipeline(adjustableSplitter.getOutputB(), sinkB.getInput(), maxFlow);
        Pipeline f = createPipeline(merger.getOutput(), sinkA.getInput(), maxFlow);

        // When
        pump.recalculateFlow();

        // Then
        assertEquals(6.f, sinkA.getFlow(), 0.001f);
        assertEquals(4.f, sinkB.getFlow(), 0.001f);

        assertEquals(6.f, merger.getFlow(), 0.001f);
        assertEquals(5.f, adjustableSplitter.getFlow(), 0.001f);
        assertEquals(10.f, fixedSplitter.getFlow(), 0.001f);

    }

    private Pipeline createPipeline(Output output, Input input, float maxFlow) {
        return new Pipeline(output, input, Collections.emptyList(), new Settings(0, maxFlow, null));
    }
}