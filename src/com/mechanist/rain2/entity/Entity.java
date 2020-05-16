package com.mechanist.rain2.entity;

import java.awt.*;

public class Entity {
    private int x;
    private int y;
    private Image entityImage;

    public Entity() {
    }

    public void loop() {
    }

    public void setEntityImage(Image img) {
        entityImage = img;
    }

    public Image getEntityImage() {
        return entityImage;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.entityImage, this.getX(), this.getY(), null);
    }



}
