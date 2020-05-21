package com.mechanist.rain2.entity;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.math.PathFinder;
import com.mechanist.rain2.rendering.TextureLoader;
import com.mechanist.rain2.tiles.Tile;

public class Spider extends Entity {
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

        for (Tile tile : RainTwo.instance.world.getTiles()) {
            if (tile.isIntersecting(getX(), getY(), getEntityImage().getWidth(null), getEntityImage().getHeight(null))) {
                t1 = tile;
            }

            if (tile.isIntersecting(RainTwo.instance.player.getX(), RainTwo.instance.player.getY(), RainTwo.instance.player.getEntityImage().getWidth(null), RainTwo.instance.player.getEntityImage().getHeight(null)) && t1 != null) {
                t2 = tile;
                break;
            }
        }

        PathFinder.Result re = null;

        if(t1 != null && t1 != t2) {
            re = PathFinder.getPath(t1, t2);
        }

        if(re != null) {
            Tile lastTile = re.r.get(re.r.size() - 1);


            moveTo(lastTile.getX(), lastTile.getY(), 10);
        }


    }


}
