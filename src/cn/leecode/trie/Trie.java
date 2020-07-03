package cn.leecode.trie;

import java.util.HashMap;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-30 20:37
 */
public class Trie<V> {
    private int size;
    private Node<V> root = new Node<>();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root.children = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value) {
        keyCheck(key);
        

        return null;
    }

    public V remove(String key) {


        return null;
    }

    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    /**
     * 传入key返回对应的节点
     *
     * @param key
     * @return
     */
    private Node<V> node(String key) {
        if (root == null) {
            return null;
        }
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            /**
             * 查看有没有对应的子节点
             */
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
            return node.word ? node : null;

        }
        return null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    private static class Node<V> {
        Node<V> parent;
        /**
         * 存储子节点
         * K -->value 根据字符找对应的子节点
         */
        HashMap<Character, Node<V>> children;
        Character character;
        V value;
        boolean word; // 是否为单词的结尾（是否为一个完整的单词）

        public Node(Node<V> parent) {
            this.parent = parent;
        }

        public Node() {

        }

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? children = new HashMap<>() : children;
        }
    }

}
