package org.mikita.datastructure.tree.segment;

import java.util.Arrays;

public abstract class AbstractArrayBasedSegmentTree implements SegmentTree {

    protected final int size;
    protected final int[] nodes;
    protected final int emptyMark;

    public AbstractArrayBasedSegmentTree(int[] data, int emptyMark) {
        size = data.length;
        this.nodes = new int[size * 4];
        this.emptyMark = emptyMark;
        Arrays.fill(nodes, emptyMark);

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

        nodes[node] = merge(nodes[node * 2 + 1], nodes[node * 2 + 2]);
    }

    @Override
    public int query(int qLeft, int qRight) {
        return query(0, 0, size - 1, qLeft, qRight);
    }

    private int query(int node, int left, int right, int qLeft, int qRight) {
        if (qLeft > right || qRight < left) {
            return emptyMark;
        }

        if (qLeft <= left && right <= qRight) {
            return nodes[node];
        }

        int mid = left + (right - left) / 2;

        return merge(
                query(node * 2 + 1, left, mid, qLeft, qRight),
                query(node * 2 + 2, mid + 1, right, qLeft, qRight)
        );
    }

    @Override
    public void set(int targetIndex, int targetValue) {
        set(0, 0, size - 1, targetIndex, targetValue);
    }

    private void set(int node, int left, int right, int targetIndex, int targetValue) {
        if(left == right) {
            nodes[node] = targetValue;
            return;
        }

        int mid = left + (right - left) / 2;

        if(targetIndex <= mid) {
            set(node * 2 + 1, left, mid, targetIndex, targetValue);
        } else {
            set(node * 2 + 2, mid + 1, right, targetIndex, targetValue);
        }

        nodes[node] = merge(nodes[node * 2 + 1], nodes[node * 2 + 2]);
    }

    @Override
    public void add(int targetIndex, int delta) {
        add(0, 0, size - 1, targetIndex, delta);
    }

    private void add(int node, int left, int right, int targetIndex, int delta) {
        if(left == right) {
            nodes[node] += delta;
            return;
        }

        int mid = left + (right - left) / 2;

        if(targetIndex <= mid) {
            add(node * 2 + 1, left, mid, targetIndex, delta);
        } else {
            add(node * 2 + 2, mid + 1, right, targetIndex, delta);
        }

        nodes[node] = merge(nodes[node * 2 + 1], nodes[node * 2 + 2]);
    }

    @Override
    public void addOnRange(int targetLeft, int targetRight, int delta) {
        addOnRange(0, 0, size - 1, targetLeft, targetRight, delta);
    }

    private void addOnRange(int node, int left, int right, int targetLeft, int targetRight, int delta) {
        if(targetLeft > right || targetRight < left) {
            return;
        }

        if(left == right) {
            nodes[node] += delta;
            return;
        }

        int mid = left + (right - left) / 2;
        addOnRange(node * 2 + 1, left, mid, targetLeft, targetRight, delta);
        addOnRange(node * 2 + 2, mid + 1, right, targetLeft, targetRight, delta);

        nodes[node] = merge(nodes[node * 2 + 1], nodes[node * 2 + 2]);
    }

    protected abstract int merge(int value1, int value2);
}