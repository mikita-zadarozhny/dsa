package org.mikita.algorithm.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    private static class Cell {
        private int[] parent;

        // position
        private int row;
        private int column;

        // distance from source
        private double g;
        // distance to destination
        private double h;
        // integral parameter of two fields above
        private double f;
    }

    private final static int[][] directions = new int[][] {
            {-1, 0}, // North
            {-1, 1}, // North-East
            {0, 1}, // East
            {1, 1}, // South-East
            {1, 0}, // South
            {1, -1}, // South-West
            {0, -1}, // West
            {-1, -1}, // North-West
    };

    private final int[][] grid;
    private final int rows;
    private final int columns;

    public AStarSearch (int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public double search(int[] source, int[] destination) {
        if(source[0] == destination[0] && source[1] == destination[1]) {
            return 0;
        }

        Cell[][] cellDetails = new Cell[rows][columns];
        boolean[][] visitedCells = new boolean[rows][columns];

        for(int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cellDetails[row][column] = createCell(row, column);
            }
        }

        Cell firstCell = cellDetails[source[0]][source[1]];
        firstCell.g = 0;
        firstCell.h = 0;
        firstCell.f = 0;
        firstCell.parent = new int[] {source[0], source[1]};

        // a.k.a. open list
        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(cell -> cell.f));
        priorityQueue.add(firstCell);

        while (!priorityQueue.isEmpty()) {
            Cell currentCell = priorityQueue.remove();
            int row = currentCell.row;
            int column = currentCell.column;
            visitedCells[row][column] = true;

            if(row == destination[0] && column == destination[1]) {
                return currentCell.g;
            }

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if(!canMove(nextRow, nextColumn)) {
                    continue;
                }

                if(visitedCells[nextRow][nextColumn]) {
                    continue;
                }

                // distance from source
                double nextG = currentCell.g + 1;
                // distance to destination
                double nextH = calculateEuclideanDistance(nextRow, nextColumn, destination);
                double nextF = nextG + nextH;

                if(cellDetails[nextRow][nextColumn].f == Double.POSITIVE_INFINITY
                        || cellDetails[nextRow][nextColumn].f > nextF) {
                    cellDetails[nextRow][nextColumn].g = nextG;
                    cellDetails[nextRow][nextColumn].h = nextH;
                    cellDetails[nextRow][nextColumn].f = nextG + nextF;
                    cellDetails[nextRow][nextColumn].parent = new int[] {row, column};
                    priorityQueue.add(cellDetails[nextRow][nextColumn]);
                }
            }
        }

        return -1;
    }

    private static Cell createCell(int row, int column) {
        Cell cell = new Cell();

        cell.row = row;
        cell.column = column;

        cell.f = Double.POSITIVE_INFINITY;
        cell.g = Double.POSITIVE_INFINITY;
        cell.h = Double.POSITIVE_INFINITY;

        return cell;
    }

    private boolean canMove(int i, int j) {
        return i >= 0 && j >= 0 && i < rows && j < columns && grid[i][j] == 0;
    }

    // Euclidean distance
    private double calculateEuclideanDistance(int row, int column, int[] destination)
    {
        return Math.sqrt((row - destination[0]) * (row - destination[0])
                + (column - destination[1]) * (column - destination[1]));
    }
}
