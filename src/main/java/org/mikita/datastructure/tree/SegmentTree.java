package org.mikita.datastructure.tree;

public class SegmentTree {

    private final int size;
    private final int[] nodes;
    private final int[] data;

    public SegmentTree(int[] data) {
        size = data.length;
        this.nodes = new int[size * 4];
        this.data = data;

        buildTree(0, 0, size - 1);
    }

    private void buildTree(int node, int left, int right) {
        if(left == right) {
            nodes[node] = data[left];
            return;
        }

        int mid = left + (right - left) / 2;

        buildTree(node * 2 + 1, left, mid);
        buildTree(node * 2 + 2, mid + 1, right);

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

    public void updateAndRebuild(int targetIndex, int targetValue) {
        data[targetIndex] = targetValue;
        buildTree(0, 0, size - 1);
    }

    public void update(int targetIndex, int targetValue) {
        update(0, 0, size - 1, targetIndex, targetValue);
    }

    private void update(int node, int left, int right, int targetIndex, int targetValue) {
        if(left == right) {
            nodes[node] = targetValue;
            data[left] = targetValue;
            return;
        }

        int mid = left + (right - left) / 2;

        if(targetIndex <= mid) {
            update(node * 2 + 1, left, mid, targetIndex, targetValue);
        } else {
            update(node * 2 + 2, mid + 1, right, targetIndex, targetValue);
        }

        nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2];
    }
}
