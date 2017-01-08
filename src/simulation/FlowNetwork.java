package simulation;

import simulation.elements.Component;
import simulation.elements.Element;
import simulation.elements.Pipeline;
import simulation.elements.Pump;
import util.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {
    private List<Component> components = new ArrayList<>();
    private List<Pipeline> pipelines = new ArrayList<>();

    public void addComponent(Component component) {
        assert component != null;

        components.add(component);
        //System.out.println(components.size() + " " + component.getPosition().x + "-" + component.getPosition().y);
    }

    public void addPipeline(Pipeline pipeline) {
        assert pipeline != null;

        pipelines.add(pipeline);
        pipeline.attach();
    }

    public void removeElement(Element element) {
        assert element != null;

        if (element instanceof Pipeline) {
            removePipeline((Pipeline) element);
        } else {
            Component component = (Component) element;
            component.setToBeDeleted();
            for (Pipeline pipeline : component.getPipelines()) {
                if (pipeline != null) {
                    removePipeline(pipeline);
                }
            }
            components.remove(component);
        }
    }

    private void removePipeline(Pipeline pipeline) {
        assert pipeline != null;

        pipeline.detach();
        pipelines.remove(pipeline);
    }


    public Element findElement(Point point) {
        assert point != null;

        for (Element element : components) {
            if (element.inBoundingArea(point)) {
                return element;
            }
        }
        for (Element element : pipelines) {
            if (element.inBoundingArea(point)) {
                return element;
            }
        }
        return null;
    }

    public Component findComponent(Point point)
    {
        assert point != null;
        for (Component comp : components) {
            if (comp.inBoundingArea(point)) {
                return comp;
            }
        }
        return null;
    }

    public boolean isOverlapping(Component component) {
        assert component != null;

        return components
                .stream()
                .anyMatch(component::isOverlapping);
    }

    //Render the components and pipelines of this flow network
    void render(Graphics g) {
        final int imageSize = 100;
        for (Component component : components) {
            g.drawImage(component.getImage(), component.getPosition().x-imageSize/2,component.getPosition().y-imageSize/2, null);
        }

        for(Pipeline pipeline : pipelines){
            for(int i =1; i < pipeline.getPoints().size(); i++)
            {
                g.drawLine(pipeline.getPoints().get(i-1).x,
                           pipeline.getPoints().get(i-1).y,
                           pipeline.getPoints().get(i).x,
                           pipeline.getPoints().get(i).y);
            }
        }

    }
}
