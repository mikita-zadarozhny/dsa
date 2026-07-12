package org.mikita.algorithm.graph.astarsearch.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.algorithm.graph.astarsearch.Path;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EuclideanAStarSearchTest {

    private static final double DELTA = 0.01;

    private static final int[][] GRID = {
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static Stream<Arguments> aStarSearchTestCases() {
        return Stream.of(
                Arguments.of(new int[] {0, 19}, new int[] {19, 0}, 29.21, 23,
                        "[0, 19] -> [1, 18] -> [1, 17] -> [1, 16] -> [2, 15] -> [3, 14] -> " +
                                "[4, 13] -> [5, 12] -> [6, 12] -> [7, 11] -> [8, 10] -> [9, 9] -> " +
                                "[10, 8] -> [11, 8] -> [12, 7] -> [13, 7] -> [14, 6] -> [15, 6] -> " +
                                "[16, 5] -> [16, 4] -> [17, 3] -> [18, 2] -> [18, 1] -> [19, 0]"),
                Arguments.of(new int[] {16, 1}, new int[] {16, 7}, 6.0, 6,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [16, 6] -> [16, 7]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 6}, 6.41, 6,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [15, 6] -> [14, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 7}, 6.82, 6,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [15, 6] -> [14, 7]"),
                Arguments.of(new int[] {16, 6}, new int[] {14, 6}, 2.0, 2,
                        "[16, 6] -> [15, 6] -> [14, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {15, 6}, 5.41, 5,
                        "[16, 1] -> [16, 2] -> [16, 3] -> [16, 4] -> [16, 5] -> [15, 6]"),
                Arguments.of(new int[] {16, 1}, new int[] {14, 1}, 2.82, 2,
                        "[16, 1] -> [15, 0] -> [14, 1]"),
                Arguments.of(new int[] {0, 19}, new int[] {0, 2}, -1, 100,
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
        EuclideanAStarSearch aStarSearch = new EuclideanAStarSearch(GRID);

        // when
        Path actual = aStarSearch.search(source, destination);

        // then
        assertEquals(expectedDistance, actual.getDistance(), DELTA);
        assertEquals(expectedPathSegments, actual.toString());
        assertTrue(expectedMinimumIterations <= actual.getIterations());
    }
}