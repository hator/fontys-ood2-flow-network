package ui;

import simulation.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

class Toolbox extends JPanel {
    private ButtonGroup buttonGroup = new ButtonGroup();
    private Consumer<Tool> selectToolCallback;

    Toolbox(Consumer<Tool> selectToolCallback) {
        this.selectToolCallback = selectToolCallback;
        initializeButtons();
    }

    private void initializeButtons() {
        final GridLayout layout = new GridLayout(0, 1, 5, 5);

        setLayout(layout);
        final AbstractButton selectionToolBtn = createAndAddButton("Selection Tool", Tool.Select);
        createAndAddButton("Removal Tool", Tool.Remove);
        createAndAddButton("Add Pump", Tool.AddPump);
        createAndAddButton("Add Sink", Tool.AddSink);
        createAndAddButton("Add Fixed Splitter", Tool.AddFixedSplitter);
        createAndAddButton("Add Adjustable Splitter", Tool.AddAdjustableSplitter);
        createAndAddButton("Add Merger", Tool.AddMerger);
        createAndAddButton("Add Pipeline", Tool.AddPipeline);

        selectButton(selectionToolBtn);
    }

    private JToggleButton createAndAddButton(String text, Tool tool) {
        final JToggleButton toolButton = new JToggleButton(text);
        toolButton.setToolTipText(text);
        toolButton.setActionCommand(tool.name());
        toolButton.addActionListener(this::onButtonClicked);
        buttonGroup.add(toolButton);
        add(toolButton);
        return toolButton;
    }

    private void selectButton(AbstractButton button) {
        buttonGroup.setSelected(button.getModel(), true);
        onButtonClicked(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, button.getActionCommand()));
    }

    private void onButtonClicked(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        Tool tool = Tool.valueOf(actionCommand);
        selectToolCallback.accept(tool);
    }
}
