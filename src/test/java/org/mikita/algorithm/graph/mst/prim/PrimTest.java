package org.mikita.algorithm.graph.mst.prim;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.algorithm.graph.mst.BaseMinimumSpanningTreeTest;

import static org.junit.jupiter.api.Assertions.*;

class PrimTest extends BaseMinimumSpanningTreeTest {

    @MethodSource("connectedGraphs")
    @ParameterizedTest
    void shouldFindMinimalSpanningTree_whenGraphIsConnected(int nodes, int[][] edges, int[][] expected) {
        // given
        Prim prim = new Prim();

        // when
        int[][] actual = prim.findMinimalSpanningTree(nodes, edgesToAdjacencyList(edges));

        // then
        assertEquals(totalWeight(expected), totalWeight(actual));
    }

    @MethodSource("disjointGraphs")
    @ParameterizedTest
    void shouldThrowException_whenGraphIsDisjoint(int nodes, int[][] edges) {
        // given
        Prim prim = new Prim();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> prim.findMinimalSpanningTree(nodes, edgesToAdjacencyList(edges)));

        // then
        assertEquals("Prim's algorithm is not compatible with disjoint graphs", exception.getMessage());
    }
}