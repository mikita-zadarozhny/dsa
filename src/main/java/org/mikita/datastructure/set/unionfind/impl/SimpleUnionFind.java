package org.mikita.datastructure.set.unionfind.impl;

import org.mikita.datastructure.set.unionfind.UnionFind;

public class SimpleUnionFind implements UnionFind {

    protected final int[] parents;

    public SimpleUnionFind(int size) {
        parents = new int[size];
        for(int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }

    @Override
    public int find(int node) {
        if(parents[node] == node) {
            return node;
        }

        // without path compression, find is O(n) in the worst case.
        return find(parents[node]);
    }

    @Override
    public void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if(rootA == rootB) {
            return;
        }

        parents[rootA] = rootB;
    }

    @Override
    public boolean connected(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        return rootA == rootB;
    }
}
