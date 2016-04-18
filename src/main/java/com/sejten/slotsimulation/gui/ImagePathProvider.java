package com.sejten.slotsimulation.gui;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotr.s
 */
class ImagePathProvider {
    private final Map<String, String> imagePaths = new HashMap<>();
    private final Map<String, Image> imageMap = new HashMap<>();

    public ImagePathProvider() {
        imagePaths.put("HIGH1", "src/main/resources/img/sym1.png");
        imagePaths.put("HIGH2", "src/main/resources/img/sym2.png");
        imagePaths.put("LOW1", "src/main/resources/img/sym3.png");
        imagePaths.put("LOW2", "src/main/resources/img/sym4.png");
        imageMap.put("HIGH1", new Image("file:" + getPathBySymbol("HIGH1")));
        imageMap.put("HIGH2", new Image("file:" + getPathBySymbol("HIGH2")));
        imageMap.put("LOW1", new Image("file:" + getPathBySymbol("LOW1")));
        imageMap.put("LOW2", new Image("file:" + getPathBySymbol("LOW2")));
    }

    private String getPathBySymbol(String symbolName) {
        return imagePaths.get(symbolName);
    }

    public Image getImageBySymbol(String symbolName) {
        return imageMap.get(symbolName);
    }
}
