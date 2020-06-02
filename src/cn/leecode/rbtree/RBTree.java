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
public class RBTree<E> extends BST<E> {
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

        super.afterAdd(node);
    }

    /**
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {

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

    private static class RBNode<E> extends Node<E> {
        //定义常量颜色
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
