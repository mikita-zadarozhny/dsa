package org.mikita.datastructure.tree.avl.impl;

import org.mikita.datastructure.tree.avl.AvlTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LinkedNodeAvlTree<T> implements AvlTree<T> {

    private class Node {
        private T value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private int height;

        private Node(T value) {
            this.value = value;
            this.height = 1;
        }

        private Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.height = 1;
        }

        private void insert(T value) {
            int comparison = comparator.compare(this.value, value);

            if(comparison < 0) {
                if(rightChild == null) {
                    rightChild = new Node(value, this);
                } else {
                    rightChild.insert(value);
                }
                height = Math.max(rightChild.height + 1, height);
            } else if(comparison > 0) {
                if(leftChild == null) {
                    leftChild = new Node(value, this);
                } else {
                    leftChild.insert(value);
                }
                height = Math.max(leftChild.height + 1, height);
            } else {
                throw new RuntimeException("Value %s is already present.".formatted(value));
            }

            if(calculateBalanceFactor() >= 2 && leftChild.calculateBalanceFactor() >= 1) {
                leftChild.ll();
            } else if(calculateBalanceFactor() <= -2 && rightChild.calculateBalanceFactor() <= -1) {
                rightChild.rr();
            }
        }

        private int calculateBalanceFactor() {
            return getLeftHeight() - getRightHeight();
        }

        private int getLeftHeight() {
            if(leftChild == null) {
                return 0;
            }
            return leftChild.height;
        }

        private int getRightHeight() {
            if(rightChild == null) {
                return 0;
            }
            return rightChild.height;
        }

        /**
         * left-left rotation

         * ## 1st case (added node with value 1), the following rotation is expected
         *       3(3)                   2(2)
         *      /                      /   \
         *    2(2)        ->       1(1)   3(1)
         *   /
         * 1(1)

         * ## 2nd case (added node with value 1), the following rotation is expected (unreal case)
         *        4(3)                    2(3)
         *       /    \                  /   \
         *     2(2)   5(1)    ->       0(1)   4(2)
         *    /    \                         /   \
         *  0(1)  3(1)                     3(1)  5(1)

         */
        private void ll() {
            Node prevParent = this.parent;
            Node prevRightChild = this.rightChild;

            this.parent = prevParent.parent;
            this.rightChild = prevParent;
            prevParent.leftChild = prevRightChild;
            prevParent.parent = this;

            if(prevRightChild != null) {
                prevRightChild.parent = prevParent;
            }

            if(this.parent != null) {
                if(this.parent.leftChild == prevParent) {
                    this.parent.leftChild = this;
                } else {
                    this.parent.rightChild = this;
                }
            } else {
                LinkedNodeAvlTree.this.root = this;
            }

            prevParent.height -= 2;
            this.height = Math.max(getLeftHeight() + 1, getRightHeight() + 1);
        }

        /**
         * right-right rotation

         * ## 1st case (added node with value 1), the following rotation is expected
         *  3(3)                        2(2)
         *     \                       /   \
         *     2(2)        ->       1(1)   3(1)
         *        \
         *        1(1)

         * ## 2nd case (added node with value 1), the following rotation is expected (unreal case)
         *        2(3)                    5(3)
         *       /    \                  /   \
         *     1(1)   5(2)    ->       2(2)  7(1)
         *           /   \            /   \
         *        3(1)   7(1)       1(1)   3(1)

         */
        private void rr() {
            Node prevParent = this.parent;
            Node prevLeftChild = this.leftChild;

            this.parent = prevParent.parent;
            this.leftChild = prevParent;

            prevParent.rightChild = prevLeftChild;
            prevParent.parent = this;

            if(prevLeftChild != null) {
                prevLeftChild.parent = prevParent;
            }

            if(this.parent != null) {
                if(this.parent.leftChild == prevParent) {
                    this.parent.leftChild = this;
                } else {
                    this.parent.rightChild = this;
                }
            } else {
                LinkedNodeAvlTree.this.root = this;
            }

            prevParent.height -= 2;
            this.height = Math.max(getLeftHeight() + 1, getRightHeight() + 1);
        }
    }

    private Comparator<T> comparator;
    private Node root;

    public LinkedNodeAvlTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insert(T value) {
        if(Objects.isNull(value)) {
            throw new RuntimeException("Value cannot be null.");
        }

        if(root == null) {
            root = new Node(value);
            return;
        }

        root.insert(value);
    }

    @Override
    public void delete(T value) {
        throw new RuntimeException("Not Implemented");
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        add(result, root);
        return result;
    }

    private void add(List<T> list, Node current) {
        if(current == null) {
            return;
        }
        list.add(current.value);
        add(list, current.leftChild);
        add(list, current.rightChild);
    }
}
