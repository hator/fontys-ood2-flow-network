package simulation.elements;

import util.Point;

import java.util.ArrayList;
import java.util.List;

abstract class Splitter extends Component {
    Input input = new Input(this);
    Output outputA = new Output(this);
    Output outputB = new Output(this);

    Splitter(Point position) {
        super(position);
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
