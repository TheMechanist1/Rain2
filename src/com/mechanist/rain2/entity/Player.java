package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.Tile;
import com.mechanist.rain2.tiles.TileTypeEnum;

public class Player extends Entity {

    private int speed = 2;

    public int corn = 0;
    public int beans = 0;
    public int wheat = 0;
    public int carrots = 0;

    public Player(double x, double y) {
        setEntityImage(new TextureLoader("textures/entity/playerEntity.png").getImage());
        setX(x);
        setY(y);
        setMaxHealth(100);
        setHealth(getMaxHealth());
    }

    @Override
    public void loop() {
        super.loop();

        double startX = getX();
        double startY = getY();

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
        Tile tileToRemove = null;
        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isIntersecting(RainTwo.instance.listener.getMouseX() - 8, RainTwo.instance.listener.getMouseY() - 35, 1, 1) && tile.isFood()) {

                if(RainTwo.instance.listener.isMouseButtonPressed(3)) {
                    if(tile.type() == TileTypeEnum.Corn) {
                        RainTwo.instance.player.corn += 1;
                    }
                    tileToRemove = tile;
                }
            }
        }
        if(tileToRemove != null) {
            RainTwo.instance.world.getTiles().remove(tileToRemove);
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
