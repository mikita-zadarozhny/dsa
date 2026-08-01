package org.mikita.algorithm.graph.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class BaseMinimumSpanningTreeTest implements GraphTestCases {

    protected List<List<int[]>> edgesToAdjacencyList(int[][] edges) {
        Map<Integer, List<int[]>> nodeToNeighborsMap = new HashMap<>();
        int maxNodeNumber = -1;

        for(int[] edge : edges) {
            nodeToNeighborsMap.putIfAbsent(edge[0], new ArrayList<>());
            nodeToNeighborsMap.putIfAbsent(edge[1], new ArrayList<>());

            nodeToNeighborsMap.get(edge[0]).add(new int[] {edge[1], edge[2]});
            nodeToNeighborsMap.get(edge[1]).add(new int[] {edge[0], edge[2]});

            maxNodeNumber = Math.max(edge[0], maxNodeNumber);
            maxNodeNumber = Math.max(edge[1], maxNodeNumber);
        }

        List<List<int[]>> adjacencyList = new ArrayList<>();
        for(int node = 0; node <= maxNodeNumber; node++) {
            adjacencyList.add(nodeToNeighborsMap.getOrDefault(node, new ArrayList<>()));
        }

        return adjacencyList;
    }

    protected int totalWeight(int[][] edges) {
        return Arrays.stream(edges)
                .mapToInt(e -> e[2])
                .sum();
    }
}
