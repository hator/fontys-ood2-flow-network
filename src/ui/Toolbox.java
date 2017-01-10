package ui;

import simulation.Settings;
import simulation.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

class Toolbox extends JPanel {
    private ButtonGroup buttonGroup = new ButtonGroup();
    private BiConsumer<Tool, Settings> selectToolCallback;
    private SettingsBox settingsBox;

    Toolbox(BiConsumer<Tool, Settings> selectToolCallback,ActionListener saveFile, ActionListener openFile) {
        this.selectToolCallback = selectToolCallback;
        initializeButtons(saveFile, openFile);
    }

    private void initializeButtons(ActionListener saveFile, ActionListener openFile) {
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
        createAndAddNonToggleButton("Save Flow Network", saveFile);
        createAndAddNonToggleButton("Load Flow Network", openFile);


        settingsBox = new SettingsBox();
        add(settingsBox);

        selectButton(selectionToolBtn);
    }

    private JButton createAndAddNonToggleButton(String text, ActionListener listener){
        JButton toolButton = new JButton(text);
        toolButton.setToolTipText(text);
        toolButton.addActionListener(listener);
        buttonGroup.add(toolButton);
        add(toolButton);
        return toolButton;
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
        selectToolCallback.accept(tool, settingsBox.getSettings());

        prepareSettingsBox(tool);

    }

    private void prepareSettingsBox(Tool tool) {
        settingsBox.setEnabled(true);
        Settings settings = new Settings();

        if (tool == Tool.Remove) {
            settingsBox.setEnabled(false);
        } else if (tool == Tool.AddAdjustableSplitter) {
            settings.splitRatio = 0.5f;
        }
        settingsBox.setCurrentSettingsReference(settings);
    }

    void setCurrentSettingsReference(Settings settings) {
        settingsBox.setCurrentSettingsReference(settings);
    }
}
