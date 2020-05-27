package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.Helper;
import com.mechanist.rain2.math.PathFinder;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.Tile;

import java.awt.*;

public class Spider extends Entity {
    public PathFinder.Result pathFinderResult = null;
    private long lastTime = System.currentTimeMillis();
    private boolean justGotHurt = false;
    private final Image hurt;
    private final Image defaultImg;
    public Spider(int x, int y) {
        hurt = new TextureLoader("textures/entity/spiderEntityHurt.png").getImage();
        defaultImg = new TextureLoader("textures/entity/spiderEntity.png").getImage();
        setEntityImage(defaultImg);
        setX(x);
        setY(y);
        setMaxHealth(50);
        setHealth(getMaxHealth());
    }

    @Override
    public void loop() {
        super.loop();

        if(RainTwo.instance.listener.isMouseButtonClicked(1) && RainTwo.instance.player.selected == 4 && !justGotHurt) {
            if(this.isIntersecting(RainTwo.instance.listener.mouseX - 16 + 4, RainTwo.instance.listener.mouseY - 48 + 8, 16, 16)) {

                setEntityImage(hurt);
                justGotHurt = true;
                lastTime = System.currentTimeMillis();
                RainTwo.instance.listener.mouseButtons1[1] = false;
                setHealth(getHealth() - 20);
                System.out.println(getHealth());
            }
        }

        if(justGotHurt && System.currentTimeMillis() - lastTime > 500) {
            lastTime = System.currentTimeMillis();
            justGotHurt = false;
            setEntityImage(defaultImg);
        }


        Tile t1 = null;
        Tile t2 = null;

//        RainTwo.instance.world.tileCoordList.get(new Helper.vector2d((int)getX(), (int)getY()));

        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isIntersecting(getX(), getY(), getEntityImage().getWidth(null), getEntityImage().getHeight(null))) {
                t1 = tile;
                break;
            }


        }
        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isFood()) {
                t2 = tile;
                break;
            }
        }

        if(t2 == null) {
            for (Tile tile : RainTwo.instance.world.getTiles()) {
                if (tile.isIntersecting(RainTwo.instance.player.getX(), RainTwo.instance.player.getY(), RainTwo.instance.player.getEntityImage().getWidth(null), RainTwo.instance.player.getEntityImage().getHeight(null))) {
                    t2 = tile;
                    break;
                }
            }
        }

        if (!this.isOnScreen(RainTwo.instance.cam, RainTwo.instance.frameWidth, RainTwo.instance.frameHeight)) {
            moveTo(RainTwo.instance.player.getX(), RainTwo.instance.player.getY(), 2);
            pathFinderResult = null;
        }

        if (t1 == null || t2 == null) return;
        pathFindMethod(t1, t2);


        if (t2.isIntersecting(this.getX(), this.getY(), getEntityImage().getWidth(null), getEntityImage().getHeight(null)) && System.currentTimeMillis() - lastTime > 1000 && t2.isFood()) {
            lastTime = System.currentTimeMillis();
            RainTwo.instance.harvested.put(new Helper.Vector2d(t2.getX(), t2.getY()), t2);
            RainTwo.instance.oppositeOfHarvested.remove(new Helper.Vector2d(t2.getX(), t2.getY()));
            RainTwo.instance.world.getTiles().remove(t2);
        }

        if(this.isIntersecting(RainTwo.instance.player) && System.currentTimeMillis() - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            RainTwo.instance.player.setHealth(RainTwo.instance.player.getHealth() - 5);
        }




    }

    public void pathFindMethod(Tile t1, Tile t2) {








        if (t1 != t2) {
            if (pathFinderResult == null || pathFinderResult.r.size() == 0) {
                pathFinderResult = PathFinder.getPath(t1, t2);
            }
//            t2.changeColor();
            if (pathFinderResult.r.size() == 0) return;
            Tile pathTile = pathFinderResult.r.get(pathFinderResult.r.size() - 1);
            double dist = Helper.distance((int) getX(), (int) getY(), pathTile.getX(), pathTile.getY());

            if (dist <= 5 && (Math.abs(getX() - pathTile.getX()) < 3 || Math.abs(getY() - pathTile.getY()) < 3)) {
                setX(pathTile.getX());
                setY(pathTile.getY());
                pathFinderResult.r.remove(pathTile);
            }
            moveTo(pathTile.getX(), pathTile.getY(), 2);
        }
    }
}

