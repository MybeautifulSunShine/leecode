package cn.leecode.map;

/**
 * 描述:
 * LinkedHashMap
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-28 14:13
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) {
                return;
            }
            node = node.next;
        }
    }

    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> node) {
        LinkedNode<K, V> node1 = (LinkedNode<K, V>) node;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) willNode;
        /**
         * 度为2的节点
         */
        if (node2 != node1) {
            //交换willNode 与 Node 在链表的位置
            //交换prev
            LinkedNode<K, V> tmp = node2.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev != null) {
                node1.prev.next = node1;
            } else {
                first = node1;
            }
            if (node2.prev != null) {
                node2.prev.next = node2;
            } else {
                first = node2;
            }
            // 交换next
        }

        LinkedNode<K, V> prev = node1.prev;
        LinkedNode<K, V> next = node1.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
    }


    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    private static class LinkedNode<K, V> extends Node<K, V> {
        private LinkedNode<K, V> prev;
        private LinkedNode<K, V> next;


        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
}
