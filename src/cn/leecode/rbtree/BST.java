package cn.leecode.rbtree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            //oldVersion1  new Node<E> (element, null)
            root = createNode(element, null);
            size++;

            // 新添加节点之后的处理
            afterAdd(root);
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点stsc

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        do {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.element = element;
                return;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        //    oldVersion1  new Node<E> (element, parent)
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加之后的方法是留给 GVL树去做的
     * 添加node之后的调整
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {
    }

    /**
     * 删除node之后的调整
     *
     * @param node 被删除的节点
     *             v2 新增一个替换要删除它的节点 reparent
     *             v3 或者是用以取代被删除节点的子节点
     */
    protected void afterRemove(Node<E> node) {

    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }

        size--;
        // 度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        // node是度为1的节点
        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            // node是度为1的节点并且是根节点
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }
            /**
             * 删除节点之后的处理 恢复平衡的操作
             */
            afterRemove(replacement);
            // node是叶子节点并且是根节点
        } else if (node.parent == null) {
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
            // node是叶子节点，但不是根节点 把线断掉
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * @Author:HeGaoJian
     * @Description: 返回兄弟节点
     * @Date: 2020/6/2 18:26
     */

    public Node<E> sibling(Node<E> node) {
        if (node.isLeftChild()) {
            return node.right;
        }
        if (node.isRightChild()) {
            return node.left;
        }
        return null;
    }


    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
