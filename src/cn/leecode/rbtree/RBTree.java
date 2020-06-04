package cn.leecode.rbtree;

import java.util.Comparator;

/**
 * 描述:
 * 红黑树
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-02 17:33
 */
public class RBTree<E> extends BBST<E> {
    /**
     * 定义常量  红色为false
     * 黑色为true
     */
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加跟删除之后 也要看一下整个树是不是平衡
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //拿到相对应的节点进行判断
        Node<E> parent = node.parent;
        /**
         * 添加的是跟节点 ,或者上溢到达了跟节点
         */
        if (parent == null) {
            black(node);
            return;
        }
        /**
         * 判断如果是黑色节点方法直接方法
         */
        if (isBlack(parent)) {
            return;
        }
        /**
         * uncle
         */
        Node<E> uncle = parent.sibling();
        /**
         * grand 只要拿到grand 就是要把节点染成红色
         */
        Node<E> grand = red(parent.parent);
        /**
         * uncle is red 节点要上溢
         */
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //grand 向上合并
            //把祖父结单当做是新添加的节点
            afterAdd(grand);
            return;
        }
        /**
         *  uncle is not red
         */
        /**
         * is  or ll rr
         */

        if (parent.isLeftChild()) {
            //l
            if (node.isLeftChild()) {
                //ll
                black(parent);
            } else {
                //lr
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            if (node.isLeftChild()) {
                //rl
                black(node);
                rotateRight(parent);
            } else {
                //rr
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    /**
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        //判断取代它的子节点是不是红色 也就是46,76  -->所以要对方法进行对应的调整
        //但是要拿到 left
        //否则删除
        /**
         * delete node is red
         */
        if (isRed(node)) {
            return;
        }
        /**
         * 用以取代node的子节点是红色
         */
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
        /**
         * delete node is root
         */
        if (parent == null) {
            return;
        }
        /**
         * delete node is black childNode
         */
        /**
         * take if brother color
         */
        /**
         *  Come here must is childNode
         *  node is childNode
         */
        Node<E> sbling = node.sibling();


    }

    /**
     * 判断是否为黑色
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断是否为为红色
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;

    }

    /**
     * 染色的方法 传一个颜色
     */
    private RBNode<E> color(Node<E> node, boolean color) {
        if (node != null) {
            ((RBNode<E>) node).color = color;
        }
        return (RBNode<E>) node;
    }

    /**
     * 染为黑色
     */
    private RBNode<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 染为红色
     */
    private RBNode<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * @Description 判断节点的颜色
     **/
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        //定义常量颜色
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

}
