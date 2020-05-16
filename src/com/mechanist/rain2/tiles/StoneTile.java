package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class StoneTile extends Tile {
    public StoneTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/stoneTile.png").getImage());
        setX(x);
        setY(y);
    }
}
