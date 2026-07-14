package org.mikita.datastructure.disjointset.unionfind.impl;

import org.mikita.datastructure.disjointset.unionfind.UnionFind;

public class PathCompressionUnionFind implements UnionFind {

    protected final int[] parents;

    public PathCompressionUnionFind(int size) {
        parents = new int[size];
        for(int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }

    // without path compression, find is O(n) in the worst case;
    // with it, operations are nearly constant time (amortized)
    @Override
    public int find(int node) {
        int root = node;

        // find root
        while(root != parents[root]) {
            root = parents[root];
        }

        // path compression
        while(node != root) {
            int parent = parents[node];
            parents[node] = root;
            node = parent;
        }

        return root;
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
