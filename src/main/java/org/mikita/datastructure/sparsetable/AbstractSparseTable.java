package org.mikita.datastructure.sparsetable;

import java.io.PrintStream;
import java.util.Arrays;

public abstract class AbstractSparseTable implements SparseTable {

    protected final int size;
    protected final int[] data;
    protected final int[][] lookupTable;
    protected final int emptyMark;

    public AbstractSparseTable(int[] data, int emptyMark) {
        size = data.length;
        this.data = data;
        lookupTable = new int[size][log2(size) + 1];
        this.emptyMark = emptyMark;

        init();
    }

    // rounded down
    protected int log2(int num) {
        return (int)(Math.log(num) / Math.log(2));
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(lookupTable[i], emptyMark);
            lookupTable[i][0] = data[i];
        }

        for(int j = 1; j < lookupTable[0].length; j++) {
            for (int i = size - 1; i >= 0; i--) {
                int joinPoint = i + (int) Math.pow(2, j - 1);
                if (joinPoint < size) {
                    lookupTable[i][j] = merge(lookupTable[i][j - 1], lookupTable[joinPoint][j - 1]);
                }
            }
        }
    }

    //O(log(N)) lookup
    public int query(int left, int right) {
        if(left < 0 || right >= size || left > right) {
            return emptyMark;
        }

        int length = right - left + 1;
        int j = log2(length);

        return merge(lookupTable[left][j], query(left + (int)Math.pow(2, j), right));
    }

    public void print(PrintStream printStream) {
        for(int[] row : lookupTable) {
            printStream.println(Arrays.toString(row));
        }
    }

    protected abstract int merge(int value1, int value2);
}
