package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ImageLibrary.initializeImages();
        SwingUtilities.invokeLater(() -> {
            final MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
