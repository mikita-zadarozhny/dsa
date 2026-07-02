package org.mikita.datastructure.tree.segment;

public interface SegmentTree {
    int query(int qLeft, int qRight);

    void set(int targetIndex, int targetValue);

    void add(int targetIndex, int delta);

    void addOnRange(int targetLeft, int targetRight, int targetValue);
}
