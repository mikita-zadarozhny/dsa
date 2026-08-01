package org.mikita.algorithm.graph.mst;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface GraphTestCases {

    static Stream<Arguments> connectedGraphs() {
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

                // 4. Single node
                Arguments.of(
                        1,
                        new int[][]{},
                        new int[][]{}
                ),

                // 5. Two nodes, one edge
                Arguments.of(
                        2,
                        new int[][]{
                                {0, 1, 5}
                        },
                        new int[][]{
                                {0, 1, 5}
                        }
                ),

                // 6. Cycle-heavy graph
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

                // 7. Negative weights
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

                // 8. Already optimal input
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

    static Stream<Arguments> disjointGraphs() {
        return Stream.of(
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
                )
        );
    }
}
