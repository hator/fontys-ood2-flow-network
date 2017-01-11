package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

abstract class Splitter extends Component implements java.io.Serializable {
    Input input;
    Output outputA;
    Output outputB;

    Splitter(Point position) {
        super(position);
        input = new Input(this, calculateSingleIOPosition(true));
        outputA = new Output(this, calculateOneOfTwoIOsPosition(false, 1));
        outputB = new Output(this, calculateOneOfTwoIOsPosition(false, 2));
    }

    Input getInput() {
        return input;
    }

    Output getOutputA() {
        return outputA;
    }

    Output getOutputB() {
        return outputB;
    }

    @Override
    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();

        pipelines.add(input.pipeline);
        pipelines.add(outputA.pipeline);
        pipelines.add(outputB.pipeline);

        return pipelines;
    }
}
