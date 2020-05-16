package com.mechanist.rain2.rendering;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.entity.Player;

public class Camera {
    public int x, y;
    Player player;

    public Camera(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public void update() {
        if(player.getX() > x + RainTwo.instance.frameWidth/2) {
            x+=player.getSpeed();
        }
        if(player.getX() < x + RainTwo.instance.frameWidth/2) {
            x-=player.getSpeed();
        }
        if(player.getY() > y + RainTwo.instance.frameHeight/2) {
            y+=player.getSpeed();
        }
        if(player.getY() < y + RainTwo.instance.frameHeight/2) {
            y-=player.getSpeed();
        }
    }
}
