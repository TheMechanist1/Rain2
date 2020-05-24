package com.mechanist.rain2.rendering.UI;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.rendering.TextureLoader;

import java.awt.*;

public class HotBar {
    Image halfHealth;
    Image fullHealth;

    Image cornIcon;
    Image beanIcon;
    Image wheatIcon;
    Image carrotIcon;

    public HotBar() {
        halfHealth = new TextureLoader("textures/ui/halfHealth.png").getImage();
        fullHealth = new TextureLoader("textures/ui/fullHealth.png").getImage();

        cornIcon = new TextureLoader("textures/ui/cornIcon.png").getImage();
        beanIcon = new TextureLoader("textures/ui/beanIcon.png").getImage();
        wheatIcon = new TextureLoader("textures/ui/wheatIcon.png").getImage();
        carrotIcon = new TextureLoader("textures/ui/carrotIcon.png").getImage();
    }

    public void render(Graphics g) {

        for(int i = 0; i < RainTwo.instance.player.getHealth()/5; i++) {
            int offset = (RainTwo.instance.frameWidth - (halfHealth.getWidth(null)*19)) / 2;
            if(i % 2 == 0) {
                g.drawImage(halfHealth, offset + i * halfHealth.getWidth(null), RainTwo.instance.frameHeight - halfHealth.getHeight(null) * 2, null);
            } else {
                g.drawImage(fullHealth, offset + i * halfHealth.getWidth(null), RainTwo.instance.frameHeight - halfHealth.getHeight(null) * 2, null);

            }
        }

        g.drawImage(cornIcon, RainTwo.instance.frameWidth/2 - (cornIcon.getWidth(null)*4), RainTwo.instance.frameHeight - cornIcon.getHeight(null), null);
        g.drawImage(beanIcon, RainTwo.instance.frameWidth/2 - (int)(beanIcon.getWidth(null)*1.5), RainTwo.instance.frameHeight - beanIcon.getHeight(null), null);
        g.drawImage(wheatIcon, RainTwo.instance.frameWidth/2 + (int)(beanIcon.getWidth(null)*1.5), RainTwo.instance.frameHeight - wheatIcon.getHeight(null), null);
        g.drawImage(carrotIcon, RainTwo.instance.frameWidth/2 + (beanIcon.getWidth(null)*4) , RainTwo.instance.frameHeight - carrotIcon.getHeight(null), null);

        g.setFont(new Font("big", Font.BOLD, 20));
        g.drawString(String.valueOf(RainTwo.instance.player.corn), RainTwo.instance.frameWidth/2 - (cornIcon.getWidth(null)*4) + 32, RainTwo.instance.frameHeight - cornIcon.getHeight(null) + 32);
        g.drawString(String.valueOf(RainTwo.instance.player.beans), RainTwo.instance.frameWidth/2 - (int)(cornIcon.getWidth(null)*1.5) + 32, RainTwo.instance.frameHeight - cornIcon.getHeight(null) + 32);
        g.drawString(String.valueOf(RainTwo.instance.player.wheat), RainTwo.instance.frameWidth/2 + (int)(cornIcon.getWidth(null)*1.5) + 32, RainTwo.instance.frameHeight - cornIcon.getHeight(null) + 32);
        g.drawString(String.valueOf(RainTwo.instance.player.carrots), RainTwo.instance.frameWidth/2 + (cornIcon.getWidth(null)*4) + 32, RainTwo.instance.frameHeight - cornIcon.getHeight(null) + 32);


    }
}
