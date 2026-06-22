package org.mikita.algorithm.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    public static Stream<Arguments> findDistanceTestCases() {
        return Stream.of(
                // start == to
                Arguments.of(
                        new int[][] {
                                {0, 1, 5}
                        },
                        0, 0, 0
                ),

                // direct edge
                Arguments.of(
                        new int[][] {
                                {0, 1, 5}
                        },
                        0, 1, 5
                ),

                // simple path through an intermediate node
                Arguments.of(
                        new int[][] {
                                {0, 1, 2},
                                {1, 2, 3}
                        },
                        0, 2, 5
                ),

                // choose shorter path among multiple options
                Arguments.of(
                        new int[][] {
                                {0, 1, 10},
                                {0, 2, 3},
                                {2, 1, 1},
                                {1, 3, 2},
                                {2, 3, 8}
                        },
                        0, 3, 6
                ),

                // graph with cycle
                Arguments.of(
                        new int[][] {
                                {0, 1, 1},
                                {1, 2, 1},
                                {2, 0, 1},
                                {1, 3, 4}
                        },
                        0, 3, 5
                ),

                // unreachable node
                Arguments.of(
                        new int[][] {
                                {0, 1, 7},
                                {1, 2, 3}
                        },
                        0, 3, Integer.MAX_VALUE
                ),

                // zero-weight edges
                Arguments.of(
                        new int[][] {
                                {0, 1, 0},
                                {1, 2, 0},
                                {0, 2, 5}
                        },
                        0, 2, 0
                ),

                // larger graph
                Arguments.of(
                        new int[][] {
                                {0, 1, 4},
                                {0, 2, 2},
                                {2, 1, 1},
                                {1, 3, 1},
                                {2, 3, 5},
                                {3, 4, 3}
                        },
                        0, 4, 7
                )
        );
    }

    @ParameterizedTest
    @MethodSource("findDistanceTestCases")
    void shouldFindDistance(int[][] nodes, int start, int to, int expectedResult) {
        // given
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.addAll(nodes);

        // when
        int actual = dijkstra.findDistance(start, to);

        // then
        assertEquals(expectedResult, actual);
    }

}