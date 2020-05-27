package com.mechanist.rain2;

import com.mechanist.rain2.entity.Entity;
import com.mechanist.rain2.entity.Player;
import com.mechanist.rain2.entity.Spider;
import com.mechanist.rain2.eventmanager.EventListener;
import com.mechanist.rain2.math.Helper;
import com.mechanist.rain2.rendering.Camera;
import com.mechanist.rain2.rendering.UI.HotBar;
import com.mechanist.rain2.terrain.World;
import com.mechanist.rain2.tiles.Food;
import com.mechanist.rain2.tiles.GrassTile;
import com.mechanist.rain2.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RainTwo extends Canvas implements Runnable {
    public static RainTwo instance;
    public final HotBar playerHotBar = new HotBar();
    public boolean running;
    public EventListener listener = new EventListener();
    public JFrame frame = new JFrame("Rain2");

    public int frameWidth = 800, frameHeight = 800;

    public World world = new World(1);
    public int currentTick = 0;
    public Player player = new Player(0, 0);
    public final Camera cam = new Camera(player);
    public ArrayList<Entity> entities = new ArrayList<>();
    private Thread thread;
    public Map<Helper.Vector2d, Tile> harvested = new HashMap<>();
    public Map<Helper.Vector2d, Tile> oppositeOfHarvested = new HashMap<>();

    //Main
    public static void main(String[] args) {
        instance = new RainTwo();
        instance.start();
    }

    // start the thread
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();

        entities.add(player);
        entities.add(new Spider(10*16, 10*16));


    }

    // stop the thread
    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, frameWidth, frameHeight);
        Tile t = new GrassTile(frameWidth / 2, frameHeight / 2);


        g.translate(-cam.x, -cam.y);
        world.render(g, cam, frameWidth, frameHeight);


        for (Entity e : entities) {
            e.draw(g);
        }
        g.translate(cam.x, cam.y);
        playerHotBar.render(g);


        if (player.getHealth() <= 0) {
            g.setFont(new Font("big", Font.BOLD, 30));
            g.drawString("Game Over. Rerun game to restart", frameWidth / 2 - 200, frameHeight / 2);
            player.setSpeed(0);
        }

        g.dispose();
        bs.show();




//        if(frame.getMousePosition() != null) listener.setMouseY(frame.getMousePosition().getLocation().y + cam.y);


    }

    public void update() {
        world.update(cam, frameWidth, frameHeight);
        cam.update();
        Point p = frame.getMousePosition();

        if (p != null) {
            listener.setMouseX(p.getLocation().x + cam.x);
            listener.setMouseY(p.getLocation().y + cam.y);
        }

        for (Entity e : entities) {
            e.loop();
        }

        entities.removeIf(entity -> entity.getHealth() <= 0);

        for(Tile t : world.getTiles()) {
            if(!(t instanceof Food)) continue;
            ((Food) t).update();
        }


    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        frame.requestFocus();

        jFrameStuff();


        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                currentTick++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    private void jFrameStuff() {
        frame.add(this);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.addKeyListener(listener);
        this.addMouseListener(listener);
        frame.setResizable(false);
        frame.pack();
        frame.requestFocusInWindow();
        frameWidth = frame.getContentPane().getSize().width;
        frameHeight = frame.getContentPane().getSize().height;


    }
}
