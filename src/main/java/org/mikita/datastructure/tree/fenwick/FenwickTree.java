package org.mikita.datastructure.tree.fenwick;

import java.util.Arrays;

/**
 * Also known as Binary Indexed Tree (BIT)
 */
public class FenwickTree {

    private final int size;
    private final int[] nodes;

    public FenwickTree(int[] data) {
        size = data.length;
        nodes = new int[size + 1];

        for(int i = 0; i < size; i++) {
            update(i, data[i]);
        }
    }

    public void update(int index, int delta) {
        index = index + 1; // 0-indexed -> 1-indexed

        while (index <= size) {
            nodes[index] += delta;
            // to move to ancestor, use the following formula:
            // ancestor(index) = index + LSB (Least Significant Bit)
            index += index & (-index);
        }
    }

    public int query(int index) {
        index = index + 1; // 0-indexed -> 1-indexed

        int result = 0;
        while (index > 0) {
            result += nodes[index];
            // to move to parent, use the following formula:
            // parent(index) = index - LSB (Least Significant Bit)
            index -= index & (-index);
        }
        return result;
    }
}
