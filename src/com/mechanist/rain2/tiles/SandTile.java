package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class SandTile extends Tile {
    public SandTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/sandTile.png").getImage());
        setX(x);
        setY(y);
    }
}
