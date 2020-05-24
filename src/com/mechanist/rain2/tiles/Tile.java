package com.mechanist.rain2.tiles;

import com.mechanist.rain2.rendering.Camera;
import com.mechanist.rain2.rendering.TextureLoader;

import java.awt.*;
import java.util.ArrayList;

public class Tile {
    public final static int tileSize = 16;
    public boolean color = false;
    private Image tileImage;
    private int x, y;

    public Tile() {
        setTileImage(null);
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image image) {
        if (image == null) {
            tileImage = new TextureLoader("textures/tile/tile.png").getImage();
        }

        tileImage = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX * 16;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY * 16;
    }

    public boolean isCollidable() {
        return false;
    }

    public boolean isFood() {
        return false;
    }

    public TileTypeEnum type() {
        return null;
    }

    public boolean exists(ArrayList<Tile> t) {
        for (Tile tt : t) {
            if (tt.getX() == this.getX() && tt.getY() == this.getY()) return true;
        }
        return false;
    }

    public boolean isOnScreen(Camera cam, int width, int height) {
        return isIntersecting(cam.x - tileSize, cam.y - tileSize, width + tileSize * 2, height + tileSize * 2);
    }

    public boolean isIntersecting(double otherX, double otherY, int otherWidth, int otherHeight) {
        return this.getX() < otherX + otherWidth &&
                this.getX() + tileImage.getWidth(null) > otherX &&
                this.getY() < otherY + otherHeight &&
                this.getY() + tileImage.getHeight(null) > otherY;
    }

    public void draw(Graphics g, Camera cam, int w, int h) {
        if (isOnScreen(cam, w, h)) g.drawImage(this.tileImage, this.getX(), this.getY(), null);


    }


}
