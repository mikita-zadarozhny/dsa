package org.mikita.algorithm.graph.shortestpath.astarsearch.impl;

import org.mikita.algorithm.graph.shortestpath.astarsearch.AbstractAStarSearch;

public class EuclideanAStarSearch extends AbstractAStarSearch {

    private final int[][] directions = new int[][] {
            {-1, 0}, // North
            {-1, 1}, // North-East
            {0, 1}, // East
            {1, 1}, // South-East
            {1, 0}, // South
            {1, -1}, // South-West
            {0, -1}, // West
            {-1, -1}, // North-West
    };

    public EuclideanAStarSearch(int[][] grid) {
        super(grid);
    }

    @Override
    protected double calculateStepDistance(int rowDelta, int columnDelta) {
        return Math.sqrt(rowDelta * rowDelta + columnDelta * columnDelta);
    }

    @Override
    protected double calculateDistanceToDestination(int row, int column, int[] destination)
    {
        return Math.sqrt((row - destination[0]) * (row - destination[0])
                + (column - destination[1]) * (column - destination[1]));
    }

    @Override
    protected int[][] getAllowedDirections() {
        return directions;
    }
}
