package com.mechanist.rain2.rendering;

import com.mechanist.rain2.RainTwo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TextureLoader {
    private final String resourceLocation;
    private static final Map<String, Image> images = new HashMap<>();

    public TextureLoader(String resLocation) {
        resourceLocation = resLocation;
    }

    public Image getImage() {
        if (images.containsKey(resourceLocation)) return images.get(resourceLocation);

        URL url = RainTwo.class.getClassLoader().getResource(resourceLocation);
        Image img;

        try {
            if (url == null)  url = RainTwo.class.getClassLoader().getResource("textures/tile/tile.png");
            img = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        images.put(resourceLocation, img);
        return img;
    }
}
