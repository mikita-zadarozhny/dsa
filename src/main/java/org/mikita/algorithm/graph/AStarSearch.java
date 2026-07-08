package org.mikita.algorithm.graph;

import java.util.*;

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

    public static class Path {
        private final boolean isFound;
        private final double distance;
        private final List<int[]> pathSegments;

        private Path(boolean isFound, double distance) {
            this.isFound = isFound;
            this.distance = distance;
            this.pathSegments = new LinkedList<>();
        }

        private void addPathSegment(int[] segment) {
            pathSegments.addFirst(segment.clone());
        }

        private static Path notFound() {
            return new Path(false, -1);
        }

        private static Path found(double distance) {
            return new Path(true, distance);
        }

        private static Path found(double distance, List<int[]> pathSegments) {
            Path path = new Path(true, distance);
            pathSegments.forEach(path::addPathSegment);
            return path;
        }

        public boolean isFound() {
            return isFound;
        }

        public double getDistance() {
            return distance;
        }

        public List<int[]> getPathSegments() {
            List<int[]> result = new ArrayList<>();
            for (int[] pathSegment : pathSegments) {
                result.add(pathSegment.clone());
            }
            return Collections.unmodifiableList(result);
        }

        public String toString() {
            if(pathSegments.isEmpty()) {
                return "[]";
            }
            if(pathSegments.size() == 1) {
                return "[%s, %s]".formatted(pathSegments.getFirst()[0], pathSegments.getFirst()[1]);
            }
            StringBuilder stringRepresentation = new StringBuilder();
            for (int i = 0; i < pathSegments.size(); i++) {
                int[] pathSegment = pathSegments.get(i);
                stringRepresentation.append("[")
                        .append(pathSegment[0]).append(", ")
                        .append(pathSegment[1])
                        .append("]");

                if(i < pathSegments.size() - 1) {
                    stringRepresentation.append(" -> ");
                }
            }

            return stringRepresentation.toString();
        }
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
        return i >= 0 && j >= 0 && i < rows && j < columns && grid[i][j] == 0;
    }

    // Euclidean distance
    private double calculateEuclideanDistance(int row, int column, int[] destination)
    {
        return Math.sqrt((row - destination[0]) * (row - destination[0])
                + (column - destination[1]) * (column - destination[1]));
    }
}
