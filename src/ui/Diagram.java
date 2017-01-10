package ui;

import simulation.Result;
import simulation.Settings;
import simulation.SimulationFacade;
import simulation.Tool;
import simulation.elements.Pipeline;
import util.Point;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

class Diagram extends JPanel {
    private Tool currentTool;
    private Settings currentSettingsReference;
    private SimulationFacade simulation;
    private Consumer<Settings> changeSettingsReferenceCallback;
    //stores all the points on the pipeline that we're currently working with
    private List<Point> pipelinePointList = new ArrayList<>();
    //stores all the pipelines


    Diagram(SimulationFacade simulation) {
        this.simulation = simulation;
        Dimension dimension = new Dimension();
        dimension.setSize(800, 600);
        setPreferredSize(dimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickCallback(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = getSize();

        //Draw background color
        g.setColor(Color.WHITE);
        g.fillRect(0,0,dim.width,dim.height);

        //change color to black to draw visible lines
        g.setColor(Color.BLACK);

        System.out.println();
        simulation.render(g);
    }



    void setChangeSettingsReferenceCallback(Consumer<Settings> changeSettingsReferenceCallback) {
        this.changeSettingsReferenceCallback = changeSettingsReferenceCallback;
    }

    private void mouseClickCallback(int x, int y) {
        assert currentTool != null;

        Point point = new Point(x, y);

        switch (currentTool) {
            case Select:
                Settings settings = simulation.select(point);
                if (settings != null) {
                    changeSettingsReferenceCallback.accept(settings);
                } else {
                }

                break;
            case Remove:
                if(simulation.remove(point) != Result.Success){
                    //On failure
                }
                break;
            case AddPipeline: {
                //TODO
                pipelinePointList.add(pipelinePointList.size()-1, point);
                simulation.applyTool(pipelinePointList, currentSettingsReference);
                break;
            }
            default:
                applyAddTool(point, currentTool);
                break;
        }
        this.repaint();
    }

    void selectTool(Tool tool, Settings settings) {
        currentTool = tool;
        currentSettingsReference = settings;
        if(pipelinePointList.size()>2)
            pipelinePointList.clear();

    }

    void handleResult(Result result) {
        //TODO implement
        if (result == Result.InvalidSettings){
            System.out.print("invalid settings");
        }
    }

    private void applyAddTool(Point point, Tool tool) {
        Result result = simulation.applyTool(point, tool, currentSettingsReference);
        if (result != Result.Success){
            handleResult(result);
        }
    }
}
