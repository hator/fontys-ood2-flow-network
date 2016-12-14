package simulation.elements;

import util.Point;

import java.util.List;

abstract public class Component extends Element {
    private Point position;

    public abstract List<Pipeline> getPipelines();
}
