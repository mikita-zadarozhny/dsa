package org.mikita.datastructure.tree.prefix.impl;

import org.mikita.datastructure.tree.prefix.PrefixTree;

import java.util.HashMap;
import java.util.Map;

public class LinkedNodePrefixTree implements PrefixTree {

    private static class Node {
        private final Map<Character, Node> children;
        private boolean wordEnd;

        private Node() {
            this.children = new HashMap<>();
            this.wordEnd = false;
        }

        private void addWord(String word, int index) {
            if(index == word.length() - 1) {
                wordEnd = true;
                return;
            }

            char nextLetter = word.charAt(index);
            children.putIfAbsent(nextLetter, new Node());
            children.get(nextLetter).addWord(word, index + 1);
        }

        private boolean exists(String word, int index) {
            if(index == word.length() - 1) {
                return wordEnd;
            }
            if(!children.containsKey(word.charAt(index))) {
                return false;
            }

            return children.get(word.charAt(index)).exists(word, index + 1);
        }
    }

    private final Map<Character, Node> children;

    public LinkedNodePrefixTree() {
        this.children = new HashMap<>();
    }

    @Override
    public void add(String word) {
        if(word == null) {
            return;
        }

        children.putIfAbsent(word.charAt(0), new Node());
        children.get(word.charAt(0)).addWord(word, 1);
    }

    @Override
    public boolean exists(String word) {
        if(word == null) {
            return false;
        }

        if(!children.containsKey(word.charAt(0))) {
            return false;
        }

        return children.get(word.charAt(0)).exists(word, 1);
    }
}
