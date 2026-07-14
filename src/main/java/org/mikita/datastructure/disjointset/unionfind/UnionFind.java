package org.mikita.datastructure.disjointset.unionfind;

public interface UnionFind {

    int find(int node);
    
    void union(int nodeA, int nodeB);

    boolean connected(int nodeA, int nodeB);
}
