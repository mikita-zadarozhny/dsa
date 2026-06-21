package org.mikita.datastructure.tree;

public class SegmentTree {

    private final int size;
    private final int[] nodes;

    public SegmentTree(int[] data) {
        size = data.length;
        this.nodes = new int[size * 4];

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

    public int query(int qLeft, int qRight) {
        return query(0, 0, size - 1, qLeft, qRight);
    }

    private int query(int node, int left, int right, int qLeft, int qRight) {
        if(qLeft > right || qRight < left) {
            return 0;
        }

        if(qLeft <= left && right <= qRight) {
            return nodes[node];
        }

        int mid = left + (right - left) / 2;

        return query(node * 2 + 1, left, mid, qLeft, qRight) +
                query(node * 2 + 2, mid + 1, right, qLeft, qRight);
    }

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

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }

    public void add(int targetIndex, int targetValue) {
        add(0, 0, size - 1, targetIndex, targetValue);
    }

    private void add(int node, int left, int right, int targetIndex, int targetValue) {
        if(left == right) {
            nodes[node] += targetValue;
            return;
        }

        int mid = left + (right - left) / 2;

        if(targetIndex <= mid) {
            add(node * 2 + 1, left, mid, targetIndex, targetValue);
        } else {
            add(node * 2 + 2, mid + 1, right, targetIndex, targetValue);
        }

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }

    public void addOnRange(int targetLeft, int targetRight, int targetValue) {
        addOnRange(0, 0, size - 1, targetLeft, targetRight, targetValue);
    }

    private void addOnRange(int node, int left, int right, int targetLeft, int targetRight, int targetValue) {
        if(targetLeft > right || targetRight < left) {
            return;
        }

        if(left == right) {
            nodes[node] += targetValue;
            return;
        }

        int mid = left + (right - left) / 2;
        addOnRange(node * 2 + 1, left, mid, targetLeft, targetRight, targetValue);
        addOnRange(node * 2 + 2, mid + 1, right, targetLeft, targetRight, targetValue);

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }
}
