package org.mikita.algorithm.graph.mst.prim;

import org.mikita.datastructure.heap.Heap;
import org.mikita.datastructure.heap.impl.ArrayHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Prim {

    public int[][] findMinimalSpanningTree(int nodes, List<List<int[]>> adjacencyList) {
        boolean[] visited = new boolean[nodes];
        Heap<int[]> heap = ArrayHeap.minHeap(Comparator.comparingInt(o -> o[2]));

        // current node, prev node, edge cost
        heap.add(new int[]{0, -1, 0});

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
            if(prevNode != -1) {
                result.add(new int[] {nodeNumber, prevNode, edgeCost});
            }

            for(int[] adjacentNode : adjacencyList.get(node[0])) {
                if(visited[adjacentNode[0]]) {
                    continue;
                }
                heap.add(new int[] {adjacentNode[0], nodeNumber, adjacentNode[1]});
            }
        }

        return result.toArray(new int[0][]);
    }
}