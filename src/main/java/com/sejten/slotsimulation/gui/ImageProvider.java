package com.sejten.slotsimulation.gui;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotr.s
 */
class ImageProvider {
    private final Map<String, String> imagePaths = new HashMap<>();
    private final Map<String, Image> imageMap = new HashMap<>();
    private final String imagesPath = "src/main/resources/img/";

    public ImageProvider() {
        try {
            Files.walk(Paths.get(imagesPath)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println("Loaded:" + filePath);
                    String filename = filePath.getFileName().toString();
                    String filepath = filePath.toString();
                    imageMap.put(filename.split("\\.")[0], new Image("file:" + filepath));
                }
            });
        } catch (IOException e) {
        }
    }

    private static final ImageProvider INSTANCE = new ImageProvider();

    public static ImageProvider getInstance() {
        return INSTANCE;
    }

    private String getPathBySymbol(String symbolName) {
        return imagePaths.get(symbolName);
    }

    public Image getImageByName(String symbolName) {
        return imageMap.get(symbolName);
    }
}
