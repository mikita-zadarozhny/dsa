package org.mikita.datastructure.sparsetable;

import java.util.Arrays;

public class SparseTable {

    public static final int EMPTY = Integer.MIN_VALUE;

    private final int size;
    private final int[] data;
    private final int[][] lookupTable;

    public SparseTable(int[] data) {
        size = data.length;
        this.data = data;
        lookupTable = new int[size][log2(size) + 1];

        init();
    }

    // rounded down
    private static int log2(int num) {
        return (int)(Math.log(num) / Math.log(2));
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(lookupTable[i], EMPTY);
            lookupTable[i][0] = data[i];
        }

        for(int j = 1; j < lookupTable[0].length; j++) {
            for (int i = size - 1; i >= 0; i--) {
                int joinPoint = i + (int) Math.pow(2, j - 1);
                if (joinPoint < size) {
                    lookupTable[i][j] = lookupTable[i][j - 1] + lookupTable[joinPoint][j - 1];
                }
            }
        }
    }

    public int query(int left, int right) {
        if(left < 0 || right >= size || left > right) {
            return 0;
        }

        int length = right - left + 1;
        int j = log2(length);

        return lookupTable[left][j] + query(left + (int)Math.pow(2, j), right);
    }
}
