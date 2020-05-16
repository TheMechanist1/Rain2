package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.Tile;

public class Player extends Entity {
    public boolean isMoving = false;
    private final int speed = 1;
    private final int velocity = -1;

    public Player(int x, int y) {
        setEntityImage(new TextureLoader("textures/player/player.png").getImage());
        setX(x);
        setY(y);
    }

    @Override
    public void loop() {
        super.loop();

        int startX = getX();
        int startY = getY();

        if (RainTwo.instance.listener.isKeyPressed(39)) {
            setX(getX() + speed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setX(tile.getX() - this.getEntityImage().getWidth(null));
            }
        }

        if (RainTwo.instance.listener.isKeyPressed(37)) {
            setX(getX() - speed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setX(tile.getX() + this.getEntityImage().getWidth(null));
            }
        }

        if (RainTwo.instance.listener.isKeyPressed(40)) {
            setY(getY() + speed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setY(tile.getY() - this.getEntityImage().getHeight(null));
            }
        }

        if (RainTwo.instance.listener.isKeyPressed(38)) {
            setY(getY() - speed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setY(tile.getY() + this.getEntityImage().getHeight(null));
            }
        }

//        if (collidingWorld() != null) {
//            setX(startX);
//            setY(startY);
//        }

    }

    public int getSpeed() {
        return speed;
    }

    public Tile collidingWorld() {
        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isCollidable() && tile.isIntersecting(getX(), getY(), getEntityImage().getWidth(null), getEntityImage().getHeight(null))) {
                return tile;
            }
        }
        return null;
    }
}
