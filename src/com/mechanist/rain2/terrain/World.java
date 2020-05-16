package com.mechanist.rain2.terrain;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.OpenSimplexNoise;
import com.mechanist.rain2.rendering.Camera;
import com.mechanist.rain2.tiles.GrassTile;
import com.mechanist.rain2.tiles.StoneTile;
import com.mechanist.rain2.tiles.Tile;
import com.mechanist.rain2.tiles.WaterTile;

import java.awt.*;
import java.util.ArrayList;


public class World {
    private int seed;
    OpenSimplexNoise n = new OpenSimplexNoise(seed);
    private final ArrayList<Tile> tiles = new ArrayList<>();

    public World(int seed) {
        this.seed = seed;
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
        int camStartX = (c.x - 1) / 16 - 1;
        int camStartY = (c.y) / 16 - 1;

        int camEndX = (c.x / 16) + RainTwo.instance.frameWidth / 16 + 2;
        int camEndY = (c.y / 16) + RainTwo.instance.frameHeight / 16 + 1;

        Tile ttt = new Tile();
        for (int x = camStartX; x < camEndX; x++) {
            for (int y = camStartY; y < camEndY; y++) {
                ttt.setX(x);
                ttt.setY(y);
                if (!ttt.exists(tiles)) {
                    double e = n.eval(x / 16.0, y / 16.0);
                    if (e < 0 && e > -0.5) {
                        tiles.add(new GrassTile(x, y));
                    } else if (e < -0.5) {
                        tiles.add(new StoneTile(x, y));
                    } else {
                        tiles.add(new WaterTile(x, y));
                    }
                }
            }
        }
    }

    public void update(Camera c, int w, int h) {
        tiles.removeIf(t -> !t.isOnScreen(c, w, h));
    }
}
