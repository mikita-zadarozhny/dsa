package org.mikita.datastructure.set.unionfind.impl;

import org.mikita.datastructure.set.unionfind.UnionFind;

public class RankedPathCompressionUnionFind implements UnionFind {

    protected final int[] parents;
    protected final int[] rank;

    public RankedPathCompressionUnionFind(int size) {
        parents = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }

    public int find(int node) {
        if(parents[node] == node) {
            return node;
        }

        // without path compression, find is O(n) in the worst case;
        // with it, operations are nearly constant time (amortized)
        int parent = find(parents[node]);
        parents[node] = parent;

        return parent;
    }

    public void union(int node1, int node2) {
        int rootA = find(node1);
        int rootB = find(node2);

        if(rootA == rootB) {
            return;
        }

        // it is better to attach the tree with smaller rank under the one with larger rank,
        // since this keeps the overall tree height small.
        // this optimization is known as "union by rank".
        if(rank[rootA] < rank[rootB]) {
            parents[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parents[rootB] = rootA;
        } else {
            parents[rootB] = rootA;
            rank[rootA]++;
        }
    }

    @Override
    public boolean connected(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        return rootA == rootB;
    }
}
