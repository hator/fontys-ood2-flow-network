package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

public class Merger extends Component {
    private Input inputA = new Input(this);
    private Input inputB = new Input(this);
    private Output output = new Output(this);

    Merger() {
        super(null, "res/merger100-100.png");
    }

   public Merger(Point position){
        super(position, "res/merger100-100.png");
    }

    Input getInputA() {
        return inputA;
    }

    Input getInputB() {
        return inputB;
    }

    @Override
    void recalculateFlow() {
        this.currentFlow = inputA.getFlow() + inputB.getFlow();
        this.output.recalculateFlow(this.currentFlow);
    }

    Output getOutput() {
        return output;
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(inputA.pipeline);
        pipelines.add(inputB.pipeline);
        pipelines.add(output.pipeline);

        return pipelines;
    }
}
