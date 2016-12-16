package simulation.elements;

import util.Point;

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
}
