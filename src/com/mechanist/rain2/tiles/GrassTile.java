package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class GrassTile extends Tile{
    public GrassTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/grassTile.png").getImage());
        setX(x);
        setY(y);
    }
}
