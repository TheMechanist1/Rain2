package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Random;

public class Food extends Tile {
    public boolean grown = false;

    public long age = 0;
    public long startTime;
    BufferedImage im;
    Random r = new Random();

    public Food() {
        startTime = System.currentTimeMillis();
    }

    public long getAge() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public void resetAge() {
        startTime = System.currentTimeMillis();
    }

    public void update() {

        if(getAge() >= (2 + (r.nextDouble()*30))) {
            grown = true;
            setTileImage(im);
        } else if(!grown){
            setTileImage(new TextureLoader("textures/tile/ungrownTile.png").getImage());
        }


    }

     BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
