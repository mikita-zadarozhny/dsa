package org.mikita.datastructure.tree.segment;

public class SegmentTree {

    private static class Node {
        private int value;
        private final int leftRangeBoundary;
        private final int rightRangeBoundary;
        private Node leftNode;
        private Node rightNode;

        private Node(int left, int right) {
            leftRangeBoundary = left;
            rightRangeBoundary = right;
        }

        private Node(int value, int left, int right) {
            this.value = value;
            leftRangeBoundary = left;
            rightRangeBoundary = right;
        }

        private static Node buildNode(int left, int right, int[] data) {
            if(left == right) {
                return new Node(data[left], left, right);
            }

            int mid = left + (right - left) / 2;

            Node node = new Node(left, right);
            node.leftNode = buildNode(left, mid, data);
            node.rightNode = buildNode(mid + 1, right, data);

            node.value = node.leftNode.value + node.rightNode.value;
            return node;
        }

        private int query(int qLeft, int qRight) {
            if(leftRangeBoundary > qRight || qLeft > rightRangeBoundary) {
                return 0;
            }

            if(qLeft <= leftRangeBoundary && rightRangeBoundary <= qRight) {
                return value;
            }

            int result = 0;

            if(leftNode != null) {
                result += leftNode.query(qLeft, qRight);
            }
            if (rightNode != null) {
                result += rightNode.query(qLeft, qRight);
            }

            return result;
        }

        private void set(int targetIndex, int targetValue) {

            if(leftRangeBoundary == rightRangeBoundary) {
                value = targetValue;
                return;
            }

            int mid = leftRangeBoundary + (rightRangeBoundary - leftRangeBoundary) / 2;
            if(targetIndex <= mid) {
                leftNode.set(targetIndex, targetValue);
            } else {
                rightNode.set(targetIndex, targetValue);
            }

            value = leftNode.value + rightNode.value;
        }
    }

    private final Node root;

    public SegmentTree(int[] data) {
        root = Node.buildNode(0, data.length - 1, data);
    }

    public int query(int qLeft, int qRight) {
        return root.query(qLeft, qRight);
    }

    public void set(int targetIndex, int targetValue) {
        root.set(targetIndex, targetValue);
    }
}
