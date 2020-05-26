package cn.leecode.avltree;

import java.util.Comparator;

/**
 * 描述:
 * Avl树
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-25 13:55
 */
public class AvlTree<E> extends BST<E> {
    /**
     * 为了能默认添加比较器 重写父类的构造函数
     */
    public AvlTree() {
        this(null);
    }

    public AvlTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 既不印象二叉搜索树 也能实现自己的方法
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //传进来的节点必然 是叶子节点 所以高度必然为1
        //恢复平衡的逻辑 不断的去寻找父节点
        while ((node = node.parent) != null) {
            //判断node是否平衡
            if (isBalance(node)) {
                //平衡 更新高度
                updateHeight(node);
            } else {
                //不平衡 恢复平衡 能来到这里就说明node是不平衡的也是高度不平衡的那个节点
                //也就是 g
                rebalance(node);
                //整棵树恢复平衡
                break;

            }

        }
    }

    /**
     * 实现他的方法返回一个自己需要类型的节点
     *
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 恢复平衡的方法
     * node 高度最低的最不平衡的那个节点   图片中的g
     */

    private void rebalance(Node<E> grand) {
        //判断 LL RR LR RL
        //p是 g这个节点中左右最高的节点
        //n 是p 最右子树高度里面最高的
        Node<E> parent = ((AVLNode<E>) grand).tollerChild();
        Node<E> node = ((AVLNode<E>) grand).tollerChild();
        //旋转方向的判断
        //L
        if (parent.isLeftChild()) {
            //判断子节点是否是左节点
            if (node.isLeftChild()) {
                //LL
            } else {
                //lR
            }
        } else { //R
            if (node.isLeftChild()) {
                //RL


            } else {
                //RR

            }
        }
    }


    /**
     * 判断是否平衡
     */
    private boolean isBalance(Node<E> node) {
        // 绝对因子 abs
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 传一个节点给我 我给你更新一下节点的高度
     * 强制装换封装
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        //高度 如果是叶子节点高度就是1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 获取平衡因子 左子树的高度减去 -->右子树的高度
         * 因为父节点 没有height 这个 属性 所以 只能 强制装换成AVLNode<E>类型的
         */
        public int balanceFactor() {
            //强制类型装换
            int leftHeight = left == null ? 0 : ((AVLNode<E>) right).height;
            //右子树的高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新自己节点的高度  每传一个节点都自动更新自己节点的高度
         */
        public void updateHeight() {
            //强制类型装换
            int leftHeight = left == null ? 0 : ((AVLNode<E>) right).height;
            //右子树的高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            //自己的高度 等于1 + 上  左子树 或者右子树中 最高的树
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tollerChild() {
            //强制类型装换
            int leftHeight = left == null ? 0 : ((AVLNode<E>) right).height;
            //右子树的高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            //那边高返回那个节点
            if (leftHeight > rightHeight) {
                return left;
            }
            if (rightHeight > leftHeight) {
                return right;
            }
            //如果相等统一返回左节点
            return isLeftChild() ? left : right;
        }
    }
}
