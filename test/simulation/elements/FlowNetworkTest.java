package simulation.elements;

import simulation.FlowNetwork;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rurarz on 14.12.2016.
 */
public class FlowNetworkTest {

    @Test
    public void removeElementTest() {
        FlowNetwork network = new FlowNetwork();

        Pump pump = new Pump(10, 10);
        Pump pump2 = new Pump(10, 10);
        Sink sink = new Sink(20);
        Merger merger = new Merger();
        Pipeline pipeline1 = new Pipeline(pump.getOutput(), merger.getInputA(), 10);
        Pipeline pipeline2 = new Pipeline(pump2.getOutput(), merger.getInputB(), 10);
        Pipeline pipeline3 = new Pipeline(merger.getOutput(), sink.getInput(), 20);

        network.addComponent(pump);
        network.addComponent(pump2);
        network.addComponent(sink);
        network.addComponent(merger);

        network.addPipeline(pipeline1);
        network.addPipeline(pipeline2);
        network.addPipeline(pipeline3);

        pump.recalculateFlow();
        pump2.recalculateFlow();

        network.removeElement(pipeline1);

        assertEquals(merger.getInputA().pipeline, null);
        assertEquals(pump.getOutput().pipeline, null);
        assertEquals(10f, sink.getFlow(), 0.001f);

        Pipeline newPipe = new Pipeline(pump.getOutput(), merger.getInputA(), 10);
        pump.recalculateFlow();

        network.removeElement(pump);

        assertEquals(merger.getInputA().pipeline, null);
        assertEquals(10f, sink.getFlow(), 0.001f);
    }

}
