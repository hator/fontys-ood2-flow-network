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

    //State0 is dark green, State1 light green, State2 red
    public enum Images {
        BarrelState0,BarrelState1,BarrelState2,
        SplitterState0, SplitterState1, SplitterState2,
        PumpState0, PumpState1, PumpState2,
        MergerState0, MergerState1, MergerState2,
        AdjustableSplitterState0, AdjustableSplitterState1, AdjustableSplitterState2

    }

    static void initializeImages() throws IOException {
        EnumMap<Images, String> paths = new EnumMap<>(Images.class);
        paths.put(Images.BarrelState0, "res/barrel_s0.png");
        paths.put(Images.SplitterState0, "res/splitter_s0.png");
        paths.put(Images.PumpState0, "res/pump_s0.png");
        paths.put(Images.MergerState0, "res/merger_s0.png");
        paths.put(Images.AdjustableSplitterState0, "res/adjustable-splitter_s0.png");
        paths.put(Images.BarrelState1, "res/barrel_s1.png");
        paths.put(Images.SplitterState1, "res/splitter_s1.png");
        paths.put(Images.PumpState1, "res/pump_s1.png");
        paths.put(Images.MergerState1, "res/merger_s1.png");
        paths.put(Images.AdjustableSplitterState1, "res/adjustable-splitter_s1.png");
        paths.put(Images.BarrelState2, "res/barrel_s2.png");
        paths.put(Images.SplitterState2, "res/splitter_s2.png");
        paths.put(Images.PumpState2, "res/pump_s2.png");
        paths.put(Images.MergerState2, "res/merger_s2.png");
        paths.put(Images.AdjustableSplitterState2, "res/adjustable-splitter_s2.png");
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
