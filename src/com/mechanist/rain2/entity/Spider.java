package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.Helper;
import com.mechanist.rain2.math.PathFinder;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.Tile;

public class Spider extends Entity {
    public PathFinder.Result pathFinderResult = null;
    private long lastTime = System.currentTimeMillis();
    public Spider(int x, int y) {
        setEntityImage(new TextureLoader("textures/entity/spiderEntity.png").getImage());
        setX(x);
        setY(y);
        setMaxHealth(50);
        setHealth(getMaxHealth());
    }

    @Override
    public void loop() {
        super.loop();


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
            if (tile.isIntersecting(RainTwo.instance.player.getX(), RainTwo.instance.player.getY(), RainTwo.instance.player.getEntityImage().getWidth(null), RainTwo.instance.player.getEntityImage().getHeight(null))) {
                t2 = tile;
                break;
            }
        }

        if (!this.isOnScreen(RainTwo.instance.cam, RainTwo.instance.frameWidth, RainTwo.instance.frameHeight)) {
            moveTo(RainTwo.instance.player.getX(), RainTwo.instance.player.getY(), 2);
            pathFinderResult = null;
        }

        if (t1 != null && t1 != t2 && t2 != null) {
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

        if(this.isIntersecting(RainTwo.instance.player) && System.currentTimeMillis() - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            RainTwo.instance.player.setHealth(RainTwo.instance.player.getHealth() - 5);
        }

        if(this.getHealth() <= 0) {
            RainTwo.instance.entities.remove(this);
        }
    }


}
