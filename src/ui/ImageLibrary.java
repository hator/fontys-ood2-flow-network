package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageLibrary {
    private static Map<String, BufferedImage> images = new HashMap<>();
    private static List<String> paths = new ArrayList<>();

    static void initializeImages(){
        paths.add("res/barrel100-100.png");
        paths.add("res/splitter100-100.png");
        paths.add("res/pump100-100.png");
        paths.add("res/merger100-100.png");
        paths.add("res/adjustable-splitter100-100.png");

        for(String path: paths){
            loadImage(path);
        }
    }
    private static void loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("File not found");

            //TODO error message + exception
        }
        images.put(path, image);
    }

    public static BufferedImage getImage(String imageName){
        return images.get(imageName);
    }
}
