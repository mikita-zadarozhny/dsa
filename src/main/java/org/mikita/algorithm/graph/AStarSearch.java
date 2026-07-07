package org.mikita.algorithm.graph;

import java.util.HashMap;
import java.util.Map;

public class AStarSearch {

    private static class Cell {
        private int[] parent;
        private double f;
        private double g;
        private double h;
    }

    private final int[][] grid;
    private final int rows;
    private final int columns;

    public AStarSearch (int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public void search(int[] src, int dest[]) {
        if(src[0] == dest[0] && src[1] == dest[1]) {
            return;
        }

        Cell[][] cellDetails = new Cell[rows][columns];

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                cell.f = Double.POSITIVE_INFINITY;
                cell.g = Double.POSITIVE_INFINITY;
                cell.h = Double.POSITIVE_INFINITY;
                cellDetails[i][j] = cell;
            }
        }

        int i = src[0];
        int j = src[1];
        cellDetails[i][j].f = 0;
        cellDetails[i][j].g = 0;
        cellDetails[i][j].h = 0;

        Map<Double, int[]> openList = new HashMap<>();
        openList.put(0.0, new int[] {i, j});

        while (!openList.isEmpty()) {

        }
    }
}
