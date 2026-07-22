package org.mikita.algorithm.graph.mst.prim;

import org.mikita.datastructure.heap.Heap;
import org.mikita.datastructure.heap.impl.ArrayHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Prim {

    public int[][] findMinimalSpanningTree(int nodes, List<List<int[]>> adjacencyList) {
        return findMinimalSpanningTree(nodes, 0, adjacencyList);
    }

    public int[][] findMinimalSpanningTree(int nodes, int startNode, List<List<int[]>> adjacencyList) {
        int leftToVisit = nodes;
        boolean[] visited = new boolean[nodes];
        Heap<int[]> heap = ArrayHeap.minHeap(Comparator.comparingInt(o -> o[2]));

        // current node, prev node, edge cost
        heap.add(new int[]{startNode, -1, 0});

        List<int[]> result = new ArrayList<>();
        while(!heap.isEmpty()) {
            int[] node = heap.poll();
            int nodeNumber = node[0];
            int prevNode = node[1];
            int edgeCost = node[2];
            if(visited[nodeNumber]) {
                continue;
            }

            visited[nodeNumber] = true;
            leftToVisit--;

            if(prevNode != -1) {
                result.add(new int[] {nodeNumber, prevNode, edgeCost});
            }

            if(adjacencyList.size() <= nodeNumber) {
                continue;
            }

            for(int[] adjacentNode : adjacencyList.get(nodeNumber)) {
                if(visited[adjacentNode[0]]) {
                    continue;
                }
                heap.add(new int[] {adjacentNode[0], nodeNumber, adjacentNode[1]});
            }
        }

        if(leftToVisit != 0) {
            throw new IllegalArgumentException("Prim's algorithm is not compatible with disjoint graphs");
        }

        return result.toArray(new int[0][]);
    }
}