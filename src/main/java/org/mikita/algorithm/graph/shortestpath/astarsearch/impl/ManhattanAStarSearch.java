package org.mikita.algorithm.graph.shortestpath.astarsearch.impl;

import org.mikita.algorithm.graph.shortestpath.astarsearch.AbstractAStarSearch;

public class ManhattanAStarSearch extends AbstractAStarSearch {

    private final int[][] directions = new int[][] {
            {-1, 0}, // North
            {0, 1}, // East
            {1, 0}, // South
            {0, -1}, // West
    };

    public ManhattanAStarSearch(int[][] grid) {
        super(grid);
    }

    @Override
    protected double calculateStepDistance(int rowDelta, int columnDelta) {
        return Math.abs(rowDelta) + Math.abs(columnDelta);
    }

    @Override
    protected double calculateDistanceToDestination(int row, int column, int[] destination) {
        return Math.abs(row - destination[0]) + Math.abs(column - destination[1]);
    }

    @Override
    protected int[][] getAllowedDirections() {
        return directions;
    }
}
