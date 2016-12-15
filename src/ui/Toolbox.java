package ui;

import javax.swing.*;
import java.awt.*;

class Toolbox extends JPanel {
    Toolbox() {
        initializeButtons();
    }

    private void initializeButtons() {
        final GridLayout layout = new GridLayout(0, 1, 5, 5);

        setLayout(layout);
        createAndAddButton("Selection Tool");
        createAndAddButton("Removal Tool");
        createAndAddButton("Add Pump");
        createAndAddButton("Add Sink");
        createAndAddButton("Add Fixed Splitter");
        createAndAddButton("Add Adjustable Splitter");
        createAndAddButton("Add Merger");
        createAndAddButton("Add Pipeline");
    }

    private void createAndAddButton(String text) {
        final JButton selectionToolBtn = new JButton(text);
        selectionToolBtn.setToolTipText(text);
        add(selectionToolBtn);
    }

}
