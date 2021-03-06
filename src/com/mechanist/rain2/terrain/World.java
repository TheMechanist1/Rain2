package com.mechanist.rain2.terrain;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.Helper;
import com.mechanist.rain2.math.OpenSimplexNoise;
import com.mechanist.rain2.rendering.Camera;
import com.mechanist.rain2.tiles.*;

import java.awt.*;
import java.util.ArrayList;


public class World {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final int seed;
    OpenSimplexNoise openSimplexNoise;

    public World(int seed) {
        this.seed = seed;
        openSimplexNoise = new OpenSimplexNoise(seed);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void render(Graphics g, Camera c, int w, int h) {
        for (Tile t : tiles) {
            t.draw(g, c, w, h);
        }
        worldGenerator(c);
    }

    public void worldGenerator(Camera c) {

        int camStartX = (c.x - 1) / Tile.tileSize - 2;
        int camStartY = (c.y) / Tile.tileSize - 2;

        int camEndX = (c.x / Tile.tileSize) + RainTwo.instance.frameWidth / Tile.tileSize + 2;
        int camEndY = (c.y / Tile.tileSize) + RainTwo.instance.frameHeight / Tile.tileSize + 2;

        Tile ttt = new Tile();
        for (int x = camStartX; x < camEndX; x++) {
            for (int y = camStartY; y < camEndY; y++) {
                ttt.setX(x);
                ttt.setY(y);

                RainTwo.instance.world.tiles.remove(RainTwo.instance.harvested.get(new Helper.Vector2d(x * 16, y * 16)));


                if (!ttt.exists(tiles)) {

                    if (RainTwo.instance.oppositeOfHarvested.containsKey(new Helper.Vector2d(x * 16, y * 16))) {
                        if (RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x * 16, y * 16)) instanceof CornTile) {
                            tiles.add(RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x*16, y*16)));
                        }
                        if (RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x * 16, y * 16)) instanceof BeanTile) {
                            tiles.add(RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x*16, y*16)));
                        }
                        if (RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x * 16, y * 16)) instanceof CarrotTile) {
                            tiles.add(RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x*16, y*16)));
                        }
                        if (RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x * 16, y * 16)) instanceof WheatTile) {
                            tiles.add(RainTwo.instance.oppositeOfHarvested.get(new Helper.Vector2d(x*16, y*16)));
                        }

                        continue;
                    }

                    double e = openSimplexNoise.eval(x / 32.0, y / 32.0);
                    e = Math.floor(e * 100);
                    if (e > 50) {
                        tiles.add(new WaterTile(x, y));
                    } else if (e > 40) {
                        tiles.add(new SandTile(x, y));
                    } else if (e > 0) {
                        tiles.add(new StoneTile(x, y));
                    } else {
                        tiles.add(new GrassTile(x, y));
                    }


                }
            }
        }

    }

    public void update(Camera c, int w, int h) {
        tiles.removeIf(tile -> !tile.isOnScreen(c, w, h));
    }
}
