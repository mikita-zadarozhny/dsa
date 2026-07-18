package org.mikita.algorithm.graph.mst.kruskal;

import org.mikita.datastructure.disjointset.unionfind.UnionFind;
import org.mikita.datastructure.disjointset.unionfind.impl.RankedPathCompressionUnionFind;
import org.mikita.datastructure.heap.Heap;
import org.mikita.datastructure.heap.impl.ArrayHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    public int[][] findMinimalSpanningTree(int nodes, int[][] edges) {
        UnionFind unionFind = new RankedPathCompressionUnionFind(nodes);
        Heap<int[]> heap = ArrayHeap.minHeap(Comparator.comparingInt(o -> o[2]));

        for(int[] edge : edges) {
            heap.add(edge);
        }

        List<int[]> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            int[] edge = heap.poll();
            if(!unionFind.connected(edge[0], edge[1])) {
                unionFind.union(edge[0], edge[1]);
                result.add(edge);
            }
        }

        return result.toArray(new int[0][]);
    }
}
