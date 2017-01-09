package ui;

import util.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class ImageLibrary {
    private static final int IMAGE_SIZE = 100;
    private static Map<Images, Image> images = new EnumMap<>(Images.class);

    public enum Images {
        Barrel,
        Splitter,
        Pump,
        Merger,
        AdjustableSplitter
    }

    static void initializeImages() throws IOException {
        EnumMap<Images, String> paths = new EnumMap<>(Images.class);
        paths.put(Images.Barrel, "res/barrel100-100.png");
        paths.put(Images.Splitter, "res/splitter100-100.png");
        paths.put(Images.Pump, "res/pump100-100.png");
        paths.put(Images.Merger, "res/merger100-100.png");
        paths.put(Images.AdjustableSplitter, "res/adjustable-splitter100-100.png");

        try {
            paths.forEach((k, path) ->
                    images.put(k, loadImage(path))
            );
        } catch (RuntimeException e) {
            throw (IOException) e.getCause(); // Rethrow as checked to regain exception safety
        }
    }

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e); // Rethrow as unchecked because of lambda functional interfaces :(
        }
    }

    public static void drawImage(Images imageName, Graphics g, Point position) {
        Image image = getImage(imageName);
        int x = position.x - IMAGE_SIZE / 2;
        int y = position.y - IMAGE_SIZE / 2;
        g.drawImage(image, x, y, null);
    }

    private static Image getImage(Images imageName) {
        return images.get(imageName);
    }
}
