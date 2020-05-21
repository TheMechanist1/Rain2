package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.tiles.Tile;

import java.awt.*;

public class Entity {
    private double x;
    private double y;
    private int health;
    private int maxHealth;
    private Image entityImage;

    public Entity() {
    }

    public void loop() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Image getEntityImage() {
        return entityImage;
    }

    public void setEntityImage(Image img) {
        entityImage = img;
    }

    public double getX() {
        return x;
    }

    public void setX(double newX) {
        x = newX;
    }

    public double getY() {
        return y;
    }

    public void setY(double newY) {
        y = newY;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void moveTo(double x, double y, double time) {
        double xSpeed = (x - this.getX());
        double ySpeed = (y - this.getY());

        double factor = time / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

        xSpeed *= factor;
        ySpeed *= factor;

        if(xSpeed > 0) {
            setX(getX() + xSpeed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setX(tile.getX() - this.getEntityImage().getWidth(null));
            }
        }

        if(xSpeed < 0) {
            setX(getX() + xSpeed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setX(tile.getX() + tile.getTileImage().getWidth(null));
            }
        }

        if(ySpeed > 0) {
            setY(getY() + ySpeed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setY(tile.getY() - this.getEntityImage().getHeight(null));
            }
        }

        if(ySpeed < 0) {
            setY(getY() + ySpeed);
            Tile tile = collidingWorld();
            if (tile != null) {
                setY(tile.getY() + tile.getTileImage().getHeight(null));
            }

        }


    }

    public Tile collidingWorld() {
        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isCollidable() && tile.isIntersecting(getX(), getY(), getEntityImage().getWidth(null), getEntityImage().getHeight(null))) {
                return tile;
            }
        }
        return null;
    }



    public void draw(Graphics g) {
        g.drawImage(this.entityImage, (int)this.getX(), (int)this.getY(), null);
    }


}
