package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.TextureLoader;

public class CornTile extends Tile{
    public CornTile(int x, int y) {
        setTileImage(new TextureLoader("textures/tile/cornTile.png").getImage());
        setX(x);
        setY(y);
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
