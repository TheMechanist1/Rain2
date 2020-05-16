package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class TestTile extends Tile{
    public TestTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/tile.png").getImage());
        setX(x);
        setY(y);
    }
}
