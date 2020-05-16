package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class WaterTile extends Tile{
    public WaterTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/waterTile.png").getImage());
        setX(x);
        setY(y);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }
}
