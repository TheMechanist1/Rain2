package com.mechanist.rain2.eventmanager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventListener implements KeyListener, MouseListener {
    public boolean[] keys = new boolean[128];
    public boolean[] mouseButtons = new boolean[10];
    public int mouseX = 0;
    public int mouseY = 0;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public boolean isKeyPressed(int key) {
        return keys[key];
    }

    public boolean isMouseButtonPressed(int key) {
        return mouseButtons[key];
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseX(int x) {
        mouseX = x;
    }

    public void setMouseY(int y) {
        mouseY = y;
    }
}
