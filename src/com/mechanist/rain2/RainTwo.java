package com.mechanist.rain2;

import com.mechanist.rain2.entity.Player;
import com.mechanist.rain2.eventmanager.EventListener;
import com.mechanist.rain2.rendering.Camera;
import com.mechanist.rain2.terrain.World;
import com.mechanist.rain2.tiles.GrassTile;
import com.mechanist.rain2.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class RainTwo extends Canvas implements Runnable {
    public static RainTwo instance;
    public Player p = new Player(0, 0);
    private Thread thread;
    private boolean running;
    public EventListener listener = new EventListener();
    public JFrame frame = new JFrame("Rain2");
    
    public int frameWidth = 800, frameHeight = 800;

    public World world = new World(0);

    // start the thread
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
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

    private final Camera cam = new Camera(0, 0, p);

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, frameWidth, frameHeight);
        Tile t = new GrassTile(frameWidth/2,frameHeight/2);

        g.translate(-cam.x, -cam.y);
        world.render(g, cam, frameWidth, frameHeight);
        p.draw(g);




        g.dispose();
        bs.show();
    }

    public void update() {
        world.update(cam, frameWidth, frameHeight);
        p.loop();
        cam.update();
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
        frame.pack();
        frameWidth = frame.getContentPane().getSize().width;
        frameHeight = frame.getContentPane().getSize().height;
        p.setX(frameWidth/2);
        p.setY(frameHeight/2);
    }

    public static void main(String[] args) {
        instance = new RainTwo();
        instance.start();
    }
}
