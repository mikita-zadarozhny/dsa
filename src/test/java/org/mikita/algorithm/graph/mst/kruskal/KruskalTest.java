package org.mikita.algorithm.graph.mst.kruskal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KruskalTest {

    public static Stream<Arguments> mstTestCases() {
        return Stream.of(

                // 1. Basic triangle
                Arguments.of(
                        3,
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {0, 2, 3}
                        },
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2}
                        }
                ),

                // 2. Unsorted edges
                Arguments.of(
                        4,
                        new int[][]{
                                {0, 1, 10},
                                {0, 2, 6},
                                {0, 3, 5},
                                {1, 3, 15},
                                {2, 3, 4}
                        },
                        new int[][]{
                                {2, 3, 4},
                                {0, 3, 5},
                                {0, 1, 10}
                        }
                ),

                // 3. Graph with multiple valid MSTs (equal weights)
                Arguments.of(
                        4,
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 1},
                                {2, 3, 1},
                                {0, 3, 1}
                        },
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 1},
                                {2, 3, 1}
                        }
                ),

                // 4. Disconnected graph (forest expected)
                Arguments.of(
                        5,
                        new int[][]{
                                {0, 1, 1},
                                {2, 3, 2}
                        },
                        new int[][]{
                                {0, 1, 1},
                                {2, 3, 2}
                        }
                ),

                // 5. Single node
                Arguments.of(
                        1,
                        new int[][]{},
                        new int[][]{}
                ),

                // 6. Two nodes, one edge
                Arguments.of(
                        2,
                        new int[][]{
                                {0, 1, 5}
                        },
                        new int[][]{
                                {0, 1, 5}
                        }
                ),

                // 7. Cycle-heavy graph
                Arguments.of(
                        4,
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {2, 3, 3},
                                {3, 0, 4},
                                {0, 2, 5}
                        },
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {2, 3, 3}
                        }
                ),

                // 8. Negative weights
                Arguments.of(
                        3,
                        new int[][]{
                                {0, 1, -2},
                                {1, 2, -1},
                                {0, 2, 4}
                        },
                        new int[][]{
                                {0, 1, -2},
                                {1, 2, -1}
                        }
                ),

                // 9. Already optimal input
                Arguments.of(
                        4,
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {2, 3, 3}
                        },
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {2, 3, 3}
                        }
                )
        );
    }

    @MethodSource("mstTestCases")
    @ParameterizedTest
    void findMinimalSpanningTree(int nodes, int[][] edges, int[][] expected) {
        // given
        Kruskal kruskal = new Kruskal();

        // when
        int[][] actual = kruskal.findMinimalSpanningTree(nodes, edges);

        //then
        assertEquals(totalWeight(expected), totalWeight(actual));
    }

    private int totalWeight(int[][] edges) {
        return Arrays.stream(edges)
                .mapToInt(e -> e[2])
                .sum();
    }
}