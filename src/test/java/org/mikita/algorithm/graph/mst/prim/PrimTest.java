package org.mikita.algorithm.graph.mst.prim;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PrimTest {

    public static Stream<Arguments> mstTestCases() {
        return Stream.of(
                // 1. Basic triangle
                Arguments.of(
                        3,
                        List.of(
                                List.of(new int[]{1, 1}, new int[]{2, 3}), // 0
                                List.of(new int[]{0, 1}, new int[]{2, 2}), // 1
                                List.of(new int[]{1, 2}, new int[]{0, 3})  // 2
                        ),
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2}
                        }
                ),

                // 2. Unsorted edges
                Arguments.of(
                        4,
                        List.of(
                                List.of(new int[]{1, 10}, new int[]{2, 6}, new int[]{3, 5}), // 0
                                List.of(new int[]{0, 10}, new int[]{3, 15}),                // 1
                                List.of(new int[]{0, 6}, new int[]{3, 4}),                 // 2
                                List.of(new int[]{0, 5}, new int[]{1, 15}, new int[]{2, 4}) // 3
                        ),
                        new int[][]{
                                {2, 3, 4},
                                {0, 3, 5},
                                {0, 1, 10}
                        }
                ),

                // 3. Multiple valid MSTs
                Arguments.of(
                        4,
                        List.of(
                                List.of(new int[]{1, 1}, new int[]{3, 1}), // 0
                                List.of(new int[]{0, 1}, new int[]{2, 1}), // 1
                                List.of(new int[]{1, 1}, new int[]{3, 1}), // 2
                                List.of(new int[]{2, 1}, new int[]{0, 1})  // 3
                        ),
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 1},
                                {2, 3, 1}
                        }
                ),

                // 4. Disconnected graph -> should fail
//                Arguments.of(
//                        5,
//                        List.of(
//                                List.of(new int[]{1, 1}), // 0
//                                List.of(new int[]{0, 1}), // 1
//                                List.of(new int[]{3, 2}), // 2
//                                List.of(new int[]{2, 2}), // 3
//                                List.of()                 // 4
//                        ),
//                        new int[][]{
//                                {0, 1, 1},
//                                {2, 3, 2}
//                        }
//                ),

                // 5. Single node
                Arguments.of(
                        1,
                        List.of(
                                List.of()
                        ),
                        new int[][]{}
                ),

                // 6. Two nodes
                Arguments.of(
                        2,
                        List.of(
                                List.of(new int[]{1, 5}), // 0
                                List.of(new int[]{0, 5})  // 1
                        ),
                        new int[][]{
                                {0, 1, 5}
                        }
                ),

                // 7. Cycle-heavy graph
                Arguments.of(
                        4,
                        List.of(
                                List.of(new int[]{1, 1}, new int[]{3, 4}, new int[]{2, 5}), // 0
                                List.of(new int[]{0, 1}, new int[]{2, 2}),                // 1
                                List.of(new int[]{1, 2}, new int[]{3, 3}, new int[]{0, 5}), // 2
                                List.of(new int[]{2, 3}, new int[]{0, 4})                 // 3
                        ),
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 2},
                                {2, 3, 3}
                        }
                ),

                // 8. Negative weights
                Arguments.of(
                        3,
                        List.of(
                                List.of(new int[]{1, -2}, new int[]{2, 4}), // 0
                                List.of(new int[]{0, -2}, new int[]{2, -1}), // 1
                                List.of(new int[]{1, -1}, new int[]{0, 4})  // 2
                        ),
                        new int[][]{
                                {0, 1, -2},
                                {1, 2, -1}
                        }
                ),

                // 9. Already optimal
                Arguments.of(
                        4,
                        List.of(
                                List.of(new int[]{1, 1}), // 0
                                List.of(new int[]{0, 1}, new int[]{2, 2}), // 1
                                List.of(new int[]{1, 2}, new int[]{3, 3}), // 2
                                List.of(new int[]{2, 3})  // 3
                        ),
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
    void findMinimalSpanningTree(int nodes, List<List<int[]>> adjacentNodes, int[][] expected) {
        // given
        Prim prim = new Prim();

        // when
        int[][] actual = prim.findMinimalSpanningTree(nodes, adjacentNodes);

        //then
        assertEquals(totalWeight(expected), totalWeight(actual));
    }

    private int totalWeight(int[][] edges) {
        return Arrays.stream(edges)
                .mapToInt(e -> e[2])
                .sum();
    }
}