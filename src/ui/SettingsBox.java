package ui;

import simulation.Settings;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

class SettingsBox extends JPanel {
    private static final String TITLE = "Settings";

    private class Input {
        private final JTextField textField;
        private final JLabel label;

        private Input(JTextField textField, JLabel label) {
            this.textField = textField;
            this.label = label;
        }

        void setEnabled(boolean enabled) {
            textField.setEnabled(enabled);
            label.setEnabled(enabled);
        }
    }

    private final Input currentFlowInput;
    private final Input maximumFlowInput;
    private final Input splitRatioInput;

    private Settings settings = new Settings();

    SettingsBox() {
        setMinimumSize(new Dimension(500, 500));

        GridLayout layout = new GridLayout(0, 2, 5, 5);
        setLayout(layout);

        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
        setBorder(border);

        this.currentFlowInput = createFieldWithLabel("Current flow", (floatVal) -> settings.currentFlow = floatVal);
        this.maximumFlowInput = createFieldWithLabel("Maximum flow", (floatVal) -> settings.maxFlow = floatVal);
        this.splitRatioInput = createFieldWithLabel("Split ratio", (floatVal) -> settings.splitRatio = floatVal);

        this.splitRatioInput.setEnabled(false);

        setCurrentSettingsReference(this.settings);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        currentFlowInput.setEnabled(enabled);
        maximumFlowInput.setEnabled(enabled);
        splitRatioInput.setEnabled(enabled);
    }

    private Input createFieldWithLabel(String text, Consumer<Float> fieldUpdateFunc) {
        JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkIsFloat();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkIsFloat();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkIsFloat();
            }

            private void checkIsFloat() {
                try {
                    Float floatVal = Float.parseFloat(textField.getText());
                    fieldUpdateFunc.accept(floatVal);
                    SettingsBox.this.resetFieldValidation(textField);
                } catch (NumberFormatException e) {
                    textField.setBackground(Color.RED);
                }
            }
        });

        JLabel label = new JLabel(text);
        label.setLabelFor(textField);

        add(label);
        add(textField);
        return new Input(textField, label);
    }

    private void resetFieldValidation(JTextField textField) {
        textField.setBackground(Color.WHITE);
    }

    void setCurrentSettingsReference(Settings settings) {
        assert settings != null;

        this.settings = settings;
        setFieldsWithSettings(settings);
        resetFieldsValidation();
    }

    private void setFieldsWithSettings(Settings settings) {
        currentFlowInput.textField.setText(Float.toString(settings.currentFlow));
        maximumFlowInput.textField.setText(Float.toString(settings.maxFlow));
        if (settings.splitRatio != null) {
            splitRatioInput.textField.setText(settings.splitRatio.toString());
            splitRatioInput.setEnabled(true);
        } else {
            splitRatioInput.textField.setText("");
            splitRatioInput.setEnabled(false);
        }
    }

    private void resetFieldsValidation() {
        resetFieldValidation(currentFlowInput.textField);
        resetFieldValidation(maximumFlowInput.textField);
        resetFieldValidation(splitRatioInput.textField);
    }

    Settings getSettings() {
        return settings;
    }

}
