package simulation;

import simulation.elements.Component;
import simulation.elements.Element;
import simulation.elements.Pipeline;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {
    private List<Component> components = new ArrayList<>();
    private List<Pipeline> pipelines = new ArrayList<>();

    public void addComponent(Component component) {
        if (component != null) {
            components.add(component);
        }
    }

    public void addPipeline(Pipeline pipeline) {
        if (pipeline != null) {
            pipelines.add(pipeline);
        }
    }

    public void removeElement(Element element) {
        if (element instanceof Pipeline) {
            removePipeline((Pipeline) element);
        } else {
            Component component = (Component) element;
            for (Pipeline pipeline : component.getPipelines()) {
                if (pipeline != null) {
                    removePipeline(pipeline);
                }
            }
            components.remove(component);
        }
    }

    private void removePipeline(Pipeline pipeline) {
        pipeline.deattach();
        pipelines.remove(pipeline);
    }

}
