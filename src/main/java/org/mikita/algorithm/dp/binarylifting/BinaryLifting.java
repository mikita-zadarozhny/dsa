package org.mikita.algorithm.dp.binarylifting;

import java.util.Arrays;

public class BinaryLifting {

    /**
     * lookupTable[i][j] -> (2^j)th ancestor of i;
     */
    private final int[][] lookupTable;
    private final int treeSize;
    private final int treeSizeLog;

    public BinaryLifting(int[] parent) {
        this.treeSize = parent.length;
        this.treeSizeLog = log2(treeSize) + 1;
        this.lookupTable = new int[treeSize][treeSizeLog];

        precompute(parent);
    }

    /**
     * rounded down
     */
    protected int log2(int num) {
        return (int) (Math.log10(num) / Math.log10(2));
    }

    /**
     * Time Complexity: O(n * log(n)), where n is the number of nodes in a tree
     * Space Complexity: O(n * log(n)), where n is the number of nodes in a tree (or depth of the tree)
     */
    private void precompute(int[] parent) {

        for (int i = 0; i < treeSize; i++) {
            Arrays.fill(lookupTable[i], -1);
            lookupTable[i][0] = parent[i];
        }

        for (int j = 1; j < treeSizeLog; j++) {
            for (int node = 0; node < treeSize; node++) {
                if (lookupTable[node][j - 1] != -1) {
                    lookupTable[node][j] = lookupTable[lookupTable[node][j - 1]][j - 1];
                }
            }
        }
    }

    public int getKthAncestor(int nodeIndex, int k) {
        for (int j = 0; j < treeSizeLog; j++) {
            if ((k & (1 << j)) != 0) {
                nodeIndex = lookupTable[nodeIndex][j];
            }
            if (nodeIndex == -1) {
                return -1;
            }
        }
        return nodeIndex;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] row : lookupTable) {
            result.append(Arrays.toString(row)).append(System.lineSeparator());
        }
        return result.toString();
    }
}
