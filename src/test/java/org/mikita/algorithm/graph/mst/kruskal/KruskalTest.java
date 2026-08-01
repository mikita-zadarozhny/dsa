package org.mikita.algorithm.graph.mst.kruskal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.algorithm.graph.mst.BaseMinimumSpanningTreeTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KruskalTest extends BaseMinimumSpanningTreeTest {

    @MethodSource(value = {"connectedGraphs", "disjointGraphs"})
    @ParameterizedTest
    void shouldFindMinimalSpanningTree_whenGraphIsEitherConnectedOrDisjoint(int nodes, int[][] edges, int[][] expected) {
        // given
        Kruskal kruskal = new Kruskal();

        // when
        int[][] actual = kruskal.findMinimalSpanningTree(nodes, edges);

        // then
        assertEquals(totalWeight(expected), totalWeight(actual));
    }
}
