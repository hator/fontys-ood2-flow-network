package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
