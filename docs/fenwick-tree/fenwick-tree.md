# Fenwick Tree

## Math behind fenwick tree

Math behind fenwick tree relies on bit operation and using a least significant bit to calculate position
either of a parent or ancestor. Let's consider the following example:

Given an array of length `16` `(0b10000)`.
For example, `[5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80]`

Given a function `reindex(int index)`, which converts zero-indexed indexes to one-indexed indexes.
Namely, it simply adds `1` to `index`.

### Update Propagation
Updating a value for some particular index requires propagation of the change to its ancestor nodes.
To find all the ancestors, use the following math:
``` Java
public void update(int index, int delta) {
    index = index + 1; // 0-indexed -> 1-indexed

    while (index <= size) {
        nodes[index] += delta;
        // to move to ancestor, use the following formula:
        // ancestor(index) = index + LSB (Least Significant Bit)
        index += index & (-index);
    }
}
```

#### Example
for input `0`: `reindex(0) = 1`
- `0b00001 + lsb(0b00001) = 0b00001 + 0b00001 = 1 + 1 = 0b00010 = 2`
- `0b00010 + lsb(0b00010) = 0b00010 + 0b00010 = 2 + 2 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `1`: `reindex(1) = 2`
- `0b00010 + lsb(0b00010) = 0b00010 + 0b00010 = 2 + 2 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `2`: `reindex(2) = 3`
- `0b00011 + lsb(0b00011) = 0b00011 + 0b00001 = 3 + 1 = 0b00100 = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `3`: `reindex(3) = 4`
- `0b00100 + lsb(0b00100) = 0b00100 + 0b00100 = 4 + 4 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `4`: `reindex(4) = 5`
- `0b00101 + lsb(0b00101) = 0b00101 + 0b00001 = 5 + 1 = 0b00110 = 6`
- `0b00110 + lsb(0b00110) = 0b00110 + 0b00010 = 6 + 2 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `5`: `reindex(5) = 6`
- `0b00110 + lsb(0b00110) = 0b00110 + 0b00010 = 6 + 2 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `6`: `reindex(6) = 7`
- `0b00111 + lsb(0b00111) = 0b00111 + 0b00001 = 7 + 1 = 0b01000 = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `7`: `reindex(7) = 8`
- `0b01000 + lsb(0b01000) = 0b01000 + 0b01000 = 8 + 8 = 0b10000 = 16`

for input `8`: `reindex(8) = 9`
- `0b01001 + lsb(0b01001) = 0b01001 + 0b00001 = 9 + 1 = 0b01010 = 10`
- `0b01010 + lsb(0b01010) = 0b01010 + 0b00010 = 10 + 2 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

for input `9`: `reindex(9) = 10`
- `0b01010 + lsb(0b01010) = 0b01010 + 0b00010 = 10 + 2 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

for input `10`: `reindex(10) = 11`
- `0b01011 + lsb(0b01011) = 0b01011 + 0b00001 = 11 + 1 = 0b01100 = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

for input `11`: `reindex(11) = 12`
- `0b01100 + lsb(0b01100) = 0b01100 + 0b00100 = 12 + 4 = 0b10000 = 16`

for input `12`: `reindex(12) = 13`
- `0b01101 + lsb(0b01101) = 0b01101 + 0b00001 = 13 + 1 = 0b01110 = 14`
- `0b01110 + lsb(0b01110) = 0b01110 + 0b00010 = 14 + 2 = 0b10000 = 16`

for input `13`: `reindex(13) = 14`
- `0b01110 + lsb(0b01110) = 0b01110 + 0b00010 = 14 + 2 = 0b10000 = 16`

for input `14`: `reindex(14) = 15`
- `0b01111 + lsb(0b01111) = 0b01111 + 0b00001 = 15 + 1 = 0b10000 = 16`

for input `15`: `reindex(15) = 16`

#### Fenwick Tree Update Schema
![Fenwick Tree Update](fenwick-tree-update.png)

### Query Calculation
**Querying prefix sum for some particular index requires checking parent nodes as well, since a node might not contain
the whole sum of range (it happens when binary representation of a number contains more than one set bit).**
To find all the children, use the following math:
``` Java
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
```

for input `0`: `reindex(0) = 1`
- `0b00001 - lsb(0b00001) = 0b00001 - 0b00001 = 1 - 1 = 0b00000 = 0`

for input `1`: `reindex(1) = 2`
- `0b00010 - lsb(0b00010) = 0b00010 - 0b00010 = 2 - 2 = 0b00000 = 0`

for input `2`: `reindex(2) = 3`
- `0b00011 - lsb(0b00011) = 0b00011 - 0b00001 = 3 - 1 = 0b00010 = 2`
- `0b00010 - lsb(0b00010) = 0b00010 - 0b00010 = 2 - 2 = 0b00000 = 0`

for input `3`: `reindex(3) = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

for input `4`: `reindex(4) = 5`
- `0b00101 - lsb(0b00101) = 0b00101 - 0b00001 = 5 - 1 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

for input `5`: `reindex(5) = 6`
- `0b00110 - lsb(0b00110) = 0b00110 - 0b00010 = 6 - 2 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

for input `6`: `reindex(6) = 7`
- `0b00111 - lsb(0b00111) = 0b00111 - 0b00001 = 7 - 1 = 0b00110 = 6`
- `0b00110 - lsb(0b00110) = 0b00110 - 0b00010 = 6 - 2 = 0b00100 = 4`
- `0b00100 - lsb(0b00100) = 0b00100 - 0b00100 = 4 - 4 = 0b00000 = 0`

for input `7`: `reindex(7) = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `8`: `reindex(8) = 9`
- `0b01001 - lsb(0b01001) = 0b01001 - 0b00001 = 9 - 1 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `9`: `reindex(9) = 10`
- `0b01010 - lsb(0b01010) = 0b01010 - 0b00010 = 10 - 2 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `10`: `reindex(10) = 11`
- `0b01011 - lsb(0b01011) = 0b01011 - 0b00001 = 11 - 1 = 0b01010 = 10`
- `0b01010 - lsb(0b01010) = 0b01010 - 0b00010 = 10 - 2 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `11`: `reindex(11) = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `12`: `reindex(12) = 13`
- `0b01101 - lsb(0b01101) = 0b01101 - 0b00001 = 13 - 1 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `13`: `reindex(13) = 14`
- `0b01110 - lsb(0b01110) = 0b01110 - 0b00010 = 14 - 2 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `14`: `reindex(14) = 15`
- `0b01111 - lsb(0b01111) = 0b01111 - 0b00001 = 15 - 1 = 0b01110 = 14`
- `0b01110 - lsb(0b01110) = 0b01110 - 0b00010 = 14 - 2 = 0b01100 = 12`
- `0b01100 - lsb(0b01100) = 0b01100 - 0b00100 = 12 - 4 = 0b01000 = 8`
- `0b01000 - lsb(0b01000) = 0b01000 - 0b01000 = 8 - 8 = 0b00000 = 0`

for input `15`: `reindex(15) = 16`
- `0b10000 - lsb(0b10000) = 0b10000 - 0b10000 = 16 - 16 = 0b00000 = 0`

#### Fenwick Tree Query Schema
![Fenwick Tree Query](fenwick-tree-query.png)
