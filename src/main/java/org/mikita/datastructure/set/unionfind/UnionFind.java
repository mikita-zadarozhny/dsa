package org.mikita.datastructure.set.unionfind;

public interface UnionFind {

    int find(int node);
    
    void union(int nodeA, int nodeB);

    boolean connected(int nodeA, int nodeB);
}
