package ui;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ImageLibrary.initializeImages();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Image files could not be loaded", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            final MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
