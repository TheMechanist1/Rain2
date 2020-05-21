package com.mechanist.rain2.math;

import com.mechanist.rain2.RainTwo;
import com.mechanist.rain2.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathFinder {
    public static class Result {
        public List<Tile> r = new ArrayList<>();
        public Tile lastTile;
        public Result(Map<Tile, Tile> parents, Tile tile) {
            lastTile = tile;
            if (!parents.containsKey(tile)) {
                r.add(tile);
            }
            while (parents.containsKey(tile)) {
                r.add(tile);
                tile = parents.get(tile);
            }
        }
    }

    public static Result getPath(Tile startTile, Tile endTile) {
        ArrayList<Tile> openList = new ArrayList<>();
        ArrayList<Tile> closedList = new ArrayList<>();
        HashMap<Tile, Double> costs = new HashMap<>();
        Map<Tile, Tile> parents = new HashMap<>();

        openList.add(startTile);
        costs.put(startTile, distance(startTile, endTile) + distance(startTile, startTile));

        while (true) {
            Map.Entry<Tile, Double> min = null;
            for (Map.Entry<Tile, Double> entry : costs.entrySet()) {
                if ((min == null || min.getValue() > entry.getValue()) && openList.contains(entry.getKey())) {
                    min = entry;
                }
            }


            assert min != null;
            Tile current = min.getKey();
            openList.remove(current);
            closedList.add(current);

            if (current == endTile) {
                return new Result(parents, endTile);
            }

            ArrayList<Tile> neighbors = new ArrayList<>();
            for (Tile tile : RainTwo.instance.world.getTiles()) {
                // 000
                //
                //
                if (tile.getX() == current.getX() - current.getTileImage().getWidth(null) && tile.getY() == current.getY() - current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }
                if (tile.getX() == current.getX() && tile.getY() == current.getY() - current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }
                if (tile.getX() == current.getX() + current.getTileImage().getWidth(null) && tile.getY() == current.getY() - current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }
                //
                //0X0
                //
                if (tile.getX() == current.getX() - current.getTileImage().getWidth(null) && tile.getY() == current.getY()) {
                    neighbors.add(tile);
                }
                if (tile.getX() == current.getX() + current.getTileImage().getWidth(null) && tile.getY() == current.getY()) {
                    neighbors.add(tile);
                }
                //
                //
                //000
                if (tile.getX() == current.getX() - current.getTileImage().getWidth(null) && tile.getY() == current.getY() + current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }
                if (tile.getX() == current.getX() && tile.getY() == current.getY() + current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }
                if (tile.getX() == current.getX() + current.getTileImage().getWidth(null) && tile.getY() == current.getY() + current.getTileImage().getHeight(null)) {
                    neighbors.add(tile);
                }

            }

            for (Tile nTile : neighbors) {
                if (closedList.contains(nTile) || nTile.isCollidable()) {
                    continue;
                }
                if (!openList.contains(nTile)) {
                    costs.put(nTile, distance(nTile, endTile) + distance(nTile, startTile));
                    parents.put(nTile, current);
                    openList.add(nTile);
                } else if (distance(nTile, endTile) + distance(nTile, startTile) < costs.get(nTile)) {
                    costs.put(nTile, distance(nTile, endTile) + distance(nTile, startTile));
                    parents.put(nTile, current);

                }

            }
        }

    }

    private static double distance(Tile start, Tile end) {
        if(start == null || end == null) {
            return -1;
        }
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }


}


