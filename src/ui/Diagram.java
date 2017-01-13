package ui;

import simulation.Result;
import simulation.Settings;
import simulation.SimulationFacade;
import simulation.Tool;
import util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

class Diagram extends JPanel {
    private Tool currentTool;
    private Settings currentSettingsReference;
    private SimulationFacade simulation;
    private Consumer<Settings> changeSettingsReferenceCallback;
    private Consumer resetToolButtonCallback;
    private boolean isResultNotSuccessful;
    private String resultMessage = "OK, press right mouse button to cancel operation.";

    Diagram(SimulationFacade simulation) {
        this.simulation = simulation;
        Dimension dimension = new Dimension();
        dimension.setSize(800, 600);
        setPreferredSize(dimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    mouseClickCallback(e.getX(), e.getY());
                } else {
                    cancelOperation();
                }
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
        setStatusLabel(g);

        simulation.render(g);
    }

    private void setStatusLabel(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        if(isResultNotSuccessful)
        {
            g.setColor(Color.RED);
        }

        g.drawString("Status: " + resultMessage, 10, 10);

    }

    void setChangeSettingsReferenceCallback(Consumer<Settings> changeSettingsReferenceCallback) {
        this.changeSettingsReferenceCallback = changeSettingsReferenceCallback;
    }

    void setCancelActionReferenceCallback(Consumer resetToolButtonCallback) {
        this.resetToolButtonCallback = resetToolButtonCallback;
    }

    private void mouseClickCallback(int x, int y) {
        assert currentTool != null;

        Point point = new Point(x, y);
        isResultNotSuccessful = false;
        switch (currentTool) {
            case Select:
                Settings settings = simulation.select(point);
                changeCurrentSettings(settings);
                break;
            case Remove:
                if(simulation.remove(point) != Result.Success){
                    //On failure
                }
                break;
            default: // Add component
                Settings newElementSettings = currentSettingsReference;
                Result result = simulation.applyTool(point, currentTool, newElementSettings);
                if (result == Result.Success) {
                    Settings newSettings = Settings.forTool(currentTool);
                    changeCurrentSettings(newSettings);
                }
                setResultMessage(result);
                break;
        }
        this.repaint();
    }

    private void cancelOperation() {
        cancelPipelineBuild();
        resetToolButtonCallback.accept(null);
    }

    private void cancelPipelineBuild(){
        simulation.cancelPipelineBuild();
        this.repaint();
    }

    private void changeCurrentSettings(Settings settings) {
        currentSettingsReference = settings;
        changeSettingsReferenceCallback.accept(settings);
    }

    void selectTool(Tool tool, Settings settings) {
        currentTool = tool;
        currentSettingsReference = settings;
        cancelPipelineBuild();
    }

    void onSettingsChanged(Result result) {
        if (result == Result.Success) {
            simulation.settingsChanged();
        }
        setResultMessage(result);
    }

    private void setResultMessage(Result result) {
        if (result == Result.Success) {
            isResultNotSuccessful = false;
            resultMessage = "OK, press right mouse button to cancel operation.";
        } else {
            isResultNotSuccessful = true;
            if (result == Result.InvalidSettings) {
                resultMessage = "Invalid settings!";
            } else if (result == Result.ComponentsOverlapping) {
                resultMessage = "Component overlapping!";
            } else if (result == Result.Failure) {
                resultMessage = "Failure!";
            }
        }
        this.repaint();
    }
}
