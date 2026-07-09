package org.mikita.algorithm.graph.astarsearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public abstract class AbstractAStarSearch implements AStarSearch {

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

    private final int[][] grid;
    private final int rows;
    private final int columns;

    public AbstractAStarSearch(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    @Override
    public Path search(int[] source, int[] destination) {
        if(source[0] == destination[0] && source[1] == destination[1]) {
            return Path.found(0, List.of(source));
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
                List<int[]> pathSegments = new ArrayList<>();
                Cell pointer = currentCell;

                while (pointer != cellDetails[pointer.parent[0]][pointer.parent[1]]) {
                    pathSegments.add(new int[] {pointer.row, pointer.column});
                    pointer = cellDetails[pointer.parent[0]][pointer.parent[1]];
                }
                pathSegments.add(new int[] {pointer.row, pointer.column});

                return Path.found(currentCell.g, pathSegments);
            }

            for (int[] direction : getAllowedDirections()) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if(!canMove(nextRow, nextColumn)) {
                    continue;
                }

                if(visitedCells[nextRow][nextColumn]) {
                    continue;
                }

                // distance from source
                double nextG = currentCell.g + calculateStepDistance(direction[0], direction[1]);
                // distance to destination
                double nextH = calculateDistanceToDestination(nextRow, nextColumn, destination);
                double nextF = nextG + nextH;

                Cell nextCell = cellDetails[nextRow][nextColumn];
                if(nextCell.f == Double.POSITIVE_INFINITY
                        || nextCell.f > nextF) {
                    nextCell.g = nextG;
                    nextCell.h = nextH;
                    nextCell.f = nextF;
                    nextCell.parent = new int[] {row, column};
                    priorityQueue.add(nextCell);
                }
            }
        }

        return Path.notFound();
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
        return i >= 0 && j >= 0 && i < rows && j < columns && grid[i][j] != 0;
    }

    protected abstract double calculateStepDistance(int rowDelta, int columnDelta);

    protected abstract double calculateDistanceToDestination(int row, int column, int[] destination);

    protected abstract int[][] getAllowedDirections();
}
