# Fenwick Tree

Fenwick Tree - compressed prefix sums.
`nodes[i] stores sum of (i - LSB(i) + 1 ... i)`
Each node - last segment of size LSB. 

## Mathematical Foundation

The Fenwick Tree (Binary Indexed Tree) is based on bitwise operations, specifically leveraging the 
least significant bit (LSB) of an index to navigate the structure. The LSB determines the size of 
the range that a node is responsible for, and it also allows efficient movement between related nodes:
- Subtracting the LSB moves to the next segment contributing to a prefix query.
- Adding the LSB moves to the next segment affected during an update.

In other words, the LSB helps compute the position of the next relevant node in either direction (query or update).
Let’s consider the following example:

Given an array of length `16` `(0b10000)`.
For example, `[5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80]`

## Indexing Convention
Fenwick Trees use 1-based indexing. It is important, because LSB logic breaks for `0`.
``` java
int reindex(int index) {
    return index + 1;
}
```

## Binary Intuition
If an index has a larger LSB, then it covers a larger segment.
If it has multiple set bits, then query decomposes it into multiple segments.
Update climbs through indices that "contain" the original position.

### Update (Point Update Propagation)
When updating a value at a specific index, it is required to propagate the change to all Fenwick Tree nodes 
whose ranges include that index. These nodes represent progressively larger segments that "cover" the updated position.
``` Java
public void update(int index, int delta) {
    index = index + 1; // 0-indexed -> 1-indexed

    while (index <= size) {
        nodes[index] += delta;
        // move to the next affected segment
        index += index & (-index);
    }
}
```

#### Example
For input `0`: `reindex(0) = 1`, it will be necessary to update every node from the following list:
- `0b00001 + lsb(0b00001) = 0b00001 + 0b00001 = 1 + 1 = 0b00010 = 2`
- `0b00010 + lsb(0b00010) = 0b00010 + 0b00010 = 2 + 2 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `1`: `reindex(1) = 2`, it will be necessary to update every node from the following list:
- `0b00010 + lsb(0b00010) = 0b00010 + 0b00010 = 2 + 2 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `2`: `reindex(2) = 3`, it will be necessary to update every node from the following list:
- `0b00011 + lsb(0b00011) = 0b00011 + 0b00001 = 3 + 1 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `3`: `reindex(3) = 4`, it will be necessary to update every node from the following list:
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `4`: `reindex(4) = 5`, it will be necessary to update every node from the following list:
- `0b00101 + lsb(0b00101) = 0b00101 + 0b00001 = 5 + 1 = 0b00110 = 6`
- `0b00110 + lsb(0b00110) = 0b00110 + 0b00010 = 6 + 2 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `5`: `reindex(5) = 6`, it will be necessary to update every node from the following list:
- `0b00110 + lsb(0b00110) = 0b00110 + 0b00010 = 6 + 2 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `6`: `reindex(6) = 7`, it will be necessary to update every node from the following list:
- `0b00111 + lsb(0b00111) = 0b00111 + 0b00001 = 7 + 1 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `7`: `reindex(7) = 8`, it will be necessary to update every node from the following list:
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

For input `8`: `reindex(8) = 9`, it will be necessary to update every node from the following list:
- `0b01001 + lsb(0b01001) = 0b01001 + 0b00001 = 9 + 1 = 0b01010 = 10`
- `0b01010 + lsb(0b01010) = 0b01010 + 0b00010 = 10 + 2 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

For input `9`: `reindex(9) = 10`, it will be necessary to update every node from the following list:
- `0b01010 + lsb(0b01010) = 0b01010 + 0b00010 = 10 + 2 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

For input `10`: `reindex(10) = 11`, it will be necessary to update every node from the following list:
- `0b01011 + lsb(0b01011) = 0b01011 + 0b00001 = 11 + 1 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

For input `11`: `reindex(11) = 12`, it will be necessary to update every node from the following list:
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

For input `12`: `reindex(12) = 13`, it will be necessary to update every node from the following list:
- `0b01101 + lsb(0b01101) = 0b01101 + 0b00001 = 13 + 1 = 0b01110 = 14`
- `0b01110 + lsb(0b01110) = 0b01110 + 0b00010 = 14 + 2 = 0b10000 = 16`

For input `13`: `reindex(13) = 14`, it will be necessary to update every node from the following list:
- `0b01110 + lsb(0b01110) = 0b01110 + 0b00010 = 14 + 2 = 0b10000 = 16`

For input `14`: `reindex(14) = 15`, it will be necessary to update every node from the following list:
- `0b01111 + lsb(0b01111) = 0b01111 + 0b00001 = 15 + 1 = 0b10000 = 16`

For input `15`: `reindex(15) = 16`, it will be necessary to update only one node `16`.

#### Fenwick Tree Update Schema
![Fenwick Tree Update](fenwick-tree-update.png)

### Query (Prefix Sum)
To compute the prefix sum up to a given index in a Fenwick Tree, it is required to iteratively accumulate contributions 
from a sequence of disjoint ranges. Each node in the tree stores the sum of a range whose size is determined by 
the least significant bit (LSB) of its index.
``` Java
public int query(int index) {
    index = index + 1; // convert from 0-based to 1-based indexing

    int result = 0;
    while (index > 0) {
        result += nodes[index];
        // move to the next contributing segment
        index -= index & (-index);
    }
    return result;
}
```

For input `0`: `reindex(0) = 1`, the following indexes should be considered:
Segments: `[1,1]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00001 - lsb(0b00001) = 0b00001 - 0b00001 = 1 - 1 = 0b00000 = 0`

For input `1`: `reindex(1) = 2`, the following indexes should be considered:
Segments: `[1,2]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00010 - lsb(0b00010) = 0b00010 - 0b00010 = 2 - 2 = 0b00000 = 0`

For input `2`: `reindex(2) = 3`, the following indexes should be considered:
Segments: `[1,2], [3,3]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00011 - lsb(0b00011) = 0b00011 - 0b00001 = 3 - 1 = 0b00010 = 2`
- `0b00010 - lsb(0b00010) = 0b00010 - 0b00010 = 2 - 2 = 0b00000 = 0`

For input `3`: `reindex(3) = 4`, the following indexes should be considered:
Segments: `[1,4]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

For input `4`: `reindex(4) = 5`,the following indexes should be considered:
Segments: `[1,4], [5,5]`
Which means that  it will be necessary to accumulate values from every node from the following list:
- `0b00101 - lsb(0b00101) = 0b00101 - 0b00001 = 5 - 1 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

For input `5`: `reindex(5) = 6`, the following indexes should be considered:
Segments: `[1,4], [5,6]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00110 - lsb(0b00110) = 0b00110 - 0b00010 = 6 - 2 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

For input `6`: `reindex(6) = 7`, the following indexes should be considered:
Segments: `[1,4], [5,6], [7,7]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b00111 - lsb(0b00111) = 0b00111 - 0b00001 = 7 - 1 = 0b00110 = 6`
- `0b00110 - lsb(0b00110) = 0b00110 - 0b00010 = 6 - 2 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

For input `7`: `reindex(7) = 8`, the following indexes should be considered:
Segments: `[1,8]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `8`: `reindex(8) = 9`, the following indexes should be considered:
Segments: `[1,8], [9,9]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01001 - lsb(0b01001) = 0b01001 - 0b00001 = 9 - 1 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `9`: `reindex(9) = 10`, the following indexes should be considered:
Segments: `[1,8], [9,10]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01010 - lsb(0b01010) = 0b01010 - 0b00010 = 10 - 2 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `10`: `reindex(10) = 11`, the following indexes should be considered:
Segments: `[1,8], [9,10], [11,11]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01011 - lsb(0b01011) = 0b01011 - 0b00001 = 11 - 1 = 0b01010 = 10`
- `0b01010 - lsb(0b01010) = 0b01010 - 0b00010 = 10 - 2 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `11`: `reindex(11) = 12`, the following indexes should be considered:
Segments: `[1,8], [9,12]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `12`: `reindex(12) = 13`, the following indexes should be considered:
Segments: `[1,8], [9,12], [13,13]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01101 - lsb(0b01101) = 0b01101 - 0b00001 = 13 - 1 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `13`: `reindex(13) = 14`, the following indexes should be considered:
Segments: `[1,8], [9,12], [13,14]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01110 - lsb(0b01110) = 0b01110 - 0b00010 = 14 - 2 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `14`: `reindex(14) = 15`, the following indexes should be considered:
Segments: `[1,8], [9,12], [13,14], [15,15]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b01111 - lsb(0b01111) = 0b01111 - 0b00001 = 15 - 1 = 0b01110 = 14`
- `0b01110 - lsb(0b01110) = 0b01110 - 0b00010 = 14 - 2 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

For input `15`: `reindex(15) = 16`, the following indexes should be considered:
Segments: `[1, 16]`
Which means that it will be necessary to accumulate values from every node from the following list:
- `0b10000 - lsb(0b10000) = 0b10000 - 0b10000 = 16 - 16 = 0b00000 = 0`

#### Fenwick Tree Query Schema
![Fenwick Tree Query](fenwick-tree-query.png)
