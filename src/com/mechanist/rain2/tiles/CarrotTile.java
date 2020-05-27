package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

import java.awt.image.BufferedImage;

public class CarrotTile extends Food {
    public CarrotTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/carrotTile.png").getImage());
        setX(x);
        setY(y);
        im = (BufferedImage) getTileImage();
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public TileTypeEnum type() {
        return TileTypeEnum.Carrot;
    }
}
