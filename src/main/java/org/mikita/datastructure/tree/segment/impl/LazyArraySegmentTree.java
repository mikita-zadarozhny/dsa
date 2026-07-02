package org.mikita.datastructure.tree.segment.impl;

import org.mikita.datastructure.tree.segment.SegmentTree;

public class LazyArraySegmentTree implements SegmentTree {

    private final int size;
    private final int[] nodes;
    private final int[] lazy;

    public LazyArraySegmentTree(int[] data) {
        size = data.length;
        this.nodes = new int[size * 4];
        this.lazy = new int[size * 4];

        buildTree(0, 0, size - 1, data);
    }

    private void buildTree(int node, int left, int right, int[] data) {
        if(left == right) {
            nodes[node] = data[left];
            return;
        }

        int mid = left + (right - left) / 2;

        buildTree(node * 2 + 1, left, mid, data);
        buildTree(node * 2 + 2, mid + 1, right, data);

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }

    @Override
    public int query(int qLeft, int qRight) {
        return query(0, 0, size - 1, qLeft, qRight);
    }

    private int query(int node, int left, int right, int qLeft, int qRight) {
        if(qLeft > right || qRight < left) {
            return 0;
        }

        if(lazy[node] != 0) {
            nodes[node] += (right - left + 1) * lazy[node];

            if(left != right) {
                lazy[node * 2 + 1] += lazy[node];
                lazy[node * 2 + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if(qLeft <= left && right <= qRight) {
            return nodes[node];
        }

        int mid = left + (right - left) / 2;

        return query(node * 2 + 1, left, mid, qLeft, qRight) +
                query(node * 2 + 2, mid + 1, right, qLeft, qRight);
    }

    @Override
    public void addOnRange(int targetLeft, int targetRight, int delta) {
        addOnRange(0, 0, size - 1, targetLeft, targetRight, delta);
    }

    private void addOnRange(int node, int left, int right, int targetLeft, int targetRight, int delta) {
        if(targetLeft > right || targetRight < left) {
            return;
        }

        if(lazy[node] != 0) {
            nodes[node] += (right - left + 1) * lazy[node];

            if(left != right) {
                lazy[node * 2 + 1] += lazy[node];
                lazy[node * 2 + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if(targetLeft <= left && right <= targetRight) {
            nodes[node] += (right - left + 1) * delta;

            if(left != right) {
                lazy[node * 2 + 1] += delta;
                lazy[node * 2 + 2] += delta;
            }
            return;
        }

        int mid = left + (right - left) / 2;
        addOnRange(node * 2 + 1, left, mid, targetLeft, targetRight, delta);
        addOnRange(node * 2 + 2, mid + 1, right, targetLeft, targetRight, delta);

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }

    @Override
    public void set(int targetIndex, int targetValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int targetIndex, int delta) {
        throw new UnsupportedOperationException();
    }
}
