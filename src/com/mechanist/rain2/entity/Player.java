package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.Helper;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.*;

public class Player extends Entity {
    public int corn = 5;
    public int beans = 5;
    public int wheat = 5;
    public int carrots = 5;
    public int selected = 0;
    private long startTime;
    private int speed = 2;

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
            if (tile.isIntersecting(RainTwo.instance.listener.getMouseX() - 8, RainTwo.instance.listener.getMouseY() - 35, 1, 1)) {
                if (RainTwo.instance.listener.isKeyPressed(3)) {
                    startTime = System.currentTimeMillis();
                }
                if (!RainTwo.instance.listener.isMouseButtonHeld(3)) startTime = System.currentTimeMillis();

                if (RainTwo.instance.listener.isMouseButtonHeld(3)) {


                    if (RainTwo.instance.oppositeOfHarvested.containsKey(new Helper.Vector2d(tile.getX(), tile.getY()))) {
                        RainTwo.instance.oppositeOfHarvested.remove(new Helper.Vector2d(tile.getX(), tile.getY()), tile);

                    }


                    if (tile instanceof CornTile) {
                        if (((Food) tile).grown) {
                            RainTwo.instance.player.corn += 2;
                        } else {
                            RainTwo.instance.player.corn += 1;
                        }


                        tileToRemove = tile;
                        RainTwo.instance.harvested.put(new Helper.Vector2d(tileToRemove.getX(), tileToRemove.getY()), tileToRemove);
                        break;

                    }
                    if (tile instanceof BeanTile) {
                        if (((Food) tile).grown) {
                            RainTwo.instance.player.beans += 2;
                        } else {
                            RainTwo.instance.player.beans += 1;
                        }
                        tileToRemove = tile;
                        RainTwo.instance.harvested.put(new Helper.Vector2d(tileToRemove.getX(), tileToRemove.getY()), tileToRemove);
                        break;

                    }
                    if (tile instanceof CarrotTile) {
                        if (((Food) tile).grown) {
                            RainTwo.instance.player.carrots += 2;
                        } else {
                            RainTwo.instance.player.carrots += 1;
                        }
                        tileToRemove = tile;
                        RainTwo.instance.harvested.put(new Helper.Vector2d(tileToRemove.getX(), tileToRemove.getY()), tileToRemove);
                        break;

                    }
                    if (tile instanceof WheatTile) {
                        if (((Food) tile).grown) {
                            RainTwo.instance.player.wheat += 2;
                        } else {
                            RainTwo.instance.player.wheat += 1;
                        }
                        tileToRemove = tile;
                        RainTwo.instance.harvested.put(new Helper.Vector2d(tileToRemove.getX(), tileToRemove.getY()), tileToRemove);
                        break;

                    }


                    if (!tile.isFood() && RainTwo.instance.player.getHealth() < RainTwo.instance.player.getMaxHealth() && System.currentTimeMillis() - startTime > 1000) {
                        switch (selected) {
                            case 0:
                                if (RainTwo.instance.player.corn <= 0) break;
                                RainTwo.instance.player.corn -= 1;
                                RainTwo.instance.player.setHealth(RainTwo.instance.player.getMaxHealth());
                                RainTwo.instance.listener.mouseButtons[3] = false;
                                break;
                            case 1:
                                if (RainTwo.instance.player.beans <= 0) break;
                                RainTwo.instance.player.beans -= 1;
                                RainTwo.instance.player.setHealth(RainTwo.instance.player.getMaxHealth());
                                RainTwo.instance.listener.mouseButtons[3] = false;
                                break;
                            case 2:
                                if (RainTwo.instance.player.wheat <= 0) break;
                                RainTwo.instance.player.wheat -= 1;
                                RainTwo.instance.player.setHealth(RainTwo.instance.player.getMaxHealth());
                                RainTwo.instance.listener.mouseButtons[3] = false;
                                break;
                            case 3:
                                if (RainTwo.instance.player.carrots <= 0) break;
                                RainTwo.instance.player.carrots -= 1;
                                RainTwo.instance.player.setHealth(RainTwo.instance.player.getMaxHealth());
                                RainTwo.instance.listener.mouseButtons[3] = false;
                                break;
                        }


                    }
                    break;
                } else if (RainTwo.instance.listener.isMouseButtonHeld(1)) {

                    if (tile instanceof GrassTile) {
                        if (selected == 0 && RainTwo.instance.player.corn > 0) {
                            RainTwo.instance.oppositeOfHarvested.put(new Helper.Vector2d(tile.getX(), tile.getY()), new CornTile(tile.getX()/16, tile.getY()/16));
                            tileToRemove = tile;
                            RainTwo.instance.player.corn -= 1;
                        } else if (selected == 1 && RainTwo.instance.player.beans > 0) {
                            RainTwo.instance.oppositeOfHarvested.put(new Helper.Vector2d(tile.getX(), tile.getY()), new BeanTile(tile.getX()/16, tile.getY()/16));
                            tileToRemove = tile;
                            RainTwo.instance.player.beans -= 1;
                        } else if (selected == 2 && RainTwo.instance.player.wheat > 0) {
                            RainTwo.instance.oppositeOfHarvested.put(new Helper.Vector2d(tile.getX(), tile.getY()), new WheatTile(tile.getX()/16, tile.getY()/16));
                            tileToRemove = tile;
                            RainTwo.instance.player.wheat -= 1;
                        } else if (selected == 3 && RainTwo.instance.player.carrots > 0) {
                            RainTwo.instance.oppositeOfHarvested.put(new Helper.Vector2d(tile.getX(), tile.getY()), new CarrotTile(tile.getX()/16, tile.getY()/16));
                            tileToRemove = tile;
                            RainTwo.instance.player.carrots -= 1;
                        }

                    }
                }
            }
        }

        if (tileToRemove != null) {
            RainTwo.instance.world.getTiles().remove(tileToRemove);
        }

        if (RainTwo.instance.listener.isKeyPressed(49)) {
            selected = 0;
        } else if (RainTwo.instance.listener.isKeyPressed(50)) {
            selected = 1;
        } else if (RainTwo.instance.listener.isKeyPressed(51)) {
            selected = 2;
        } else if (RainTwo.instance.listener.isKeyPressed(52)) {
            selected = 3;
        } else if (RainTwo.instance.listener.isKeyPressed(53)) {
            selected = 4;
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
