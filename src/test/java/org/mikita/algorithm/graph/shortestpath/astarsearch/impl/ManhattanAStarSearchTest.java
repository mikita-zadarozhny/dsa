package org.mikita.algorithm.graph.shortestpath.astarsearch.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.algorithm.graph.shortestpath.astarsearch.Path;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManhattanAStarSearchTest {

    private static final double DELTA = 0.01;

    private static final int[][] GRID = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1}
    };

    public static Stream<Arguments> aStarSearchTestCases() {
        return Stream.of(
                Arguments.of(new int[] {0, 19}, new int[] {19, 0}, 38.0, 38,
                        "[0, 19] -> [1, 19] -> [1, 18] -> [1, 17] -> [1, 16] -> [1, 15] -> " +
                                "[2, 15] -> [2, 14] -> [3, 14] -> [4, 14] -> [4, 13] -> [4, 12] -> " +
                                "[5, 12] -> [6, 12] -> [7, 12] -> [7, 11] -> [8, 11] -> [8, 10] -> " +
                                "[8, 9] -> [9, 9] -> [10, 9] -> [10, 8] -> [11, 8] -> [12, 8] -> " +
                                "[12, 7] -> [12, 6] -> [12, 5] -> [12, 4] -> [12, 3] -> [13, 3] -> " +
                                "[14, 3] -> [14, 2] -> [14, 1] -> [14, 0] -> [15, 0] -> [16, 0] -> " +
                                "[17, 0] -> [18, 0] -> [19, 0]"),
                Arguments.of(new int[] {16, 1}, new int[] {16, 7}, 6.0, 6,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [16, 6] -> [16, 7]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 6}, 7.0, 7,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [16, 6] -> [15, 6] -> [14, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 7}, 8.0, 8,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [16, 6] -> [15, 6] -> [14, 6] -> [14, 7]"),
                Arguments.of(new int[] {16, 6}, new int[] {14, 6}, 2.0, 2,
                        "[16, 6] -> [15, 6] -> [14, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {15, 6}, 6.0, 6,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [16, 6] -> [15, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 1}, 4.0, 4,
                        "[16, 1] -> [16, 0] -> [15, 0] -> [14, 0] -> [14, 1]"),
                Arguments.of(new int[] {0, 19}, new int[] {19, 19}, -1, 100,
                        "[]"),
                Arguments.of(new int[] {0, 1}, new int[] {1, 1}, -1, 0,
                        "[]"),
                Arguments.of(new int[] {0, 0}, new int[] {0, 0}, 0, 0,
                        "[0, 0]")
        );
    }

    @MethodSource("aStarSearchTestCases")
    @ParameterizedTest
    void shouldSearchPath(int[] source, int[] destination, double expectedDistance,
                          long expectedMinimumIterations, String expectedPathSegments) {

        // given
        ManhattanAStarSearch aStarSearch = new ManhattanAStarSearch(GRID);

        // when
        Path actual = aStarSearch.search(source, destination);

        // then
        assertEquals(expectedDistance, actual.getDistance(), DELTA);
        assertEquals(expectedDistance != -1.0, actual.isFound());
        assertEquals(expectedPathSegments, actual.toString());
        assertTrue(expectedMinimumIterations <= actual.getIterations());
    }
}