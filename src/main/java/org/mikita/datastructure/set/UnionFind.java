package org.mikita.datastructure.set;

public class UnionFind {

    protected final int[] parents;

    public UnionFind(int size) {
        parents = new int[size];
        for(int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }

    public int find(int node) {
        if(parents[node] == node) {
            return node;
        }

        //path compression
        int parent = find(parents[node]);
        parents[node] = parent;

        return parent;
    }

    public void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);

        if(parent1 == parent2) {
            return;
        }

        //it is better to join a smaller set in a bigger one,
        // since it would save time on path compressing.
        parents[parent1] = parent2;
    }
}
