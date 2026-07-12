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
Explanation is [here](docs/fenwick-tree/fenwick-tree.md).

### Sets

#### UnionFind (a.k.a. DisjointSet Union) 
- [SimpleUnionFind](src/main/java/org/mikita/datastructure/set/unionfind/impl/SimpleUnionFind.java) -
This implementation does not use path compression or union by rank. 
As a result, trees can become deep, and operations may degrade to linear time (O(n)) in the worst case.
- [PathCompressionUnionFind](src/main/java/org/mikita/datastructure/set/unionfind/impl/PathCompressionUnionFind.java) -
This implementation uses path compression to flatten trees during find operations, 
improving performance in practice. However, it does not use union by rank, 
so worst-case guarantees are weaker.
- [RankedPathCompressionUnionFind](src/main/java/org/mikita/datastructure/set/unionfind/impl/RankedPathCompressionUnionFind.java) -
This implementation combines path compression and union by rank to keep trees shallow. 
It provides near-constant amortized time complexity for all operations.

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
Search algorithm used for finding the shortest path in a weighted graph.
- [EuclideanAStarSearch](src/main/java/org/mikita/algorithm/graph/astarsearch/impl/EuclideanAStarSearch.java) - 
Informed search algorithm used for finding the shortest path in a weighted graph.
This algorithm is an extended version of Dijkstra's Algorithm with a heuristic (based on Euclidean distance) 
for improved efficiency.
- [ManhattanAStarSearch](src/main/java/org/mikita/algorithm/graph/astarsearch/impl/ManhattanAStarSearch.java) -
Informed search algorithm used for finding the shortest path in a weighted graph.
This algorithm is an extended version of Dijkstra's Algorithm with a heuristic (based on Manhattan distance) 
for improved efficiency.
