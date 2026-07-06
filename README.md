# dsa

## Data Structures

### Sparse Tables
- [RangeSumSparseTable](src/main/java/org/mikita/datastructure/sparsetable/impl/RangeSumSparseTable.java) -
Sparse table implementation for looking for a sum of elements of a sub-array.
- [MaxValueSparseTable](src/main/java/org/mikita/datastructure/sparsetable/impl/MaxValueSparseTable.java) -
Sparse table implementation for looking for the maximal element within a sub-array.
- [MinValueSparseTable](src/main/java/org/mikita/datastructure/sparsetable/impl/MinValueSparseTable.java) -
Sparse table implementation for looking for the minimal elements within a sub-array.

### Trees

#### Segment Trees
- [RangeSumArraySegmentTree](src/main/java/org/mikita/datastructure/tree/segment/impl/RangeSumArraySegmentTree.java) - 
Array-based implementation for a segment tree data structure for looking for a sum of elements of a sub-array.
- [MaxValueArraySegmentTree](src/main/java/org/mikita/datastructure/tree/segment/impl/MaxValueArraySegmentTree.java) -
Array-based implementation for a segment tree data structure for looking for the maximal element within a sub-array.
- [MinValueArraySegmentTree.java](src/main/java/org/mikita/datastructure/tree/segment/impl/MinValueArraySegmentTree.java) -
Array-based implementation for a segment tree data structure for looking for the minimal element within a sub-array.
- [LazyArraySegmentTree](src/main/java/org/mikita/datastructure/tree/segment/impl/LazyArraySegmentTree.java) - 
Array-based implementation for a segment tree data structure with lazy propagation for range update operation.
- [LinkedNodeSegmentTree](src/main/java/org/mikita/datastructure/tree/segment/impl/LinkedNodeSegmentTree.java) -
Linked Node-based implementation for a segment tree data structure.

#### Fenwick Trees
- [FenwickTree](src/main/java/org/mikita/datastructure/tree/fenwick/FenwickTree.java) -
Array-based fenwick tree implementation for updatable prefix sum data structure.

### Sets
- [UnionFind (a.k.a. DisjointSet)](src/main/java/org/mikita/datastructure/set/UnionFind.java) - 
Data structure designed to union sets and check whether two elements are parts of the same set in logarithmic time.
Path compression is included into this implementation. Ranking is not included into this implementation. 

## Algorithms

### Array
- [Kadane](src/main/java/org/mikita/algorithm/array/Kadane.java) -
Array-based algorithm used for finding max sub-array sum.
  - Time Complexity is O(n).
  - Space Complexity is O(1).
- [DutchNationalFlag](src/main/java/org/mikita/algorithm/array/DutchNationalFlag.java) - 
Array-based algorithm used for efficient sorting for arrays that consists only of 0s, 1s, and 2s.
  - Time Complexity is O(n).
  - Space Complexity is O(1).

### Graph
- [Dijkstra](src/main/java/org/mikita/algorithm/graph/Dijkstra.java) -
Graph-based algorithm used for searching the shortest path in a weighted graph.
