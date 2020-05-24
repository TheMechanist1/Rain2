package com.mechanist.rain2.rendering;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.entity.Player;

public class Camera {
    public int x, y;
    Player player;

    public Camera(Player player) {
        this.x = (int)player.getX() - 392;
        this.y = (int)player.getY() - 378;
        this.player = player;

    }

    public void update() {
        if(player.getX() > x + RainTwo.instance.frameWidth/2.0) {
            x+=player.getSpeed();
        }
        if(player.getX() < x + RainTwo.instance.frameWidth/2.0) {
            x-=player.getSpeed();
        }
        if(player.getY() > y + RainTwo.instance.frameHeight/2.0) {
            y+=player.getSpeed();
        }
        if(player.getY() < y + RainTwo.instance.frameHeight/2.0) {
            y-=player.getSpeed();
        }
    }
}
