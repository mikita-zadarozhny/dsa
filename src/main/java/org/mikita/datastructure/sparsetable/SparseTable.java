package org.mikita.datastructure.sparsetable;

import java.io.PrintStream;

public interface SparseTable {

    int query(int left, int right);

    void print(PrintStream printStream);
}
