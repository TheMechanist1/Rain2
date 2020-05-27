package com.mechanist.rain2.math;

import com.mechanist.rain2.tiles.Tile;

import java.util.Objects;

public class Helper {

    public static double distance(int startX, int startY, int endX, int endY) {
        return Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
    }

    public static double distance(Tile start, Tile end) {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public static class Vector2d {
        private final int x, y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vector2d vector2d = (Vector2d) o;
            return x == vector2d.x &&
                    y == vector2d.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Vector2d(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}
