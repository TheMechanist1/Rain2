package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

import java.awt.image.BufferedImage;

public class CornTile extends Food {
    public CornTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/cornTile.png").getImage());
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
        return TileTypeEnum.Corn;
    }
}
