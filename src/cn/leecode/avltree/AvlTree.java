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
     * 删除之后的平衡处理
     *
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        //不断的往上找查看失衡的节点
        while ((node = node.parent) != null) {
            //判断是否失去平衡
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                rebalance(node);
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
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        //旋转方向的判断
        //L
        if (parent.isLeftChild()) {
            //判断子节点是否是左节点
            if (node.isLeftChild()) {
                //LL 对g进行右旋转
                rotateRight(grand);
            } else {
                //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            //R
            if (node.isLeftChild()) {
                //RL 先对parent进行右旋转
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * version2
     *
     * @param grand
     */
    private void rebalance2(Node<E> grand) {
        //判断 LL RR LR RL
        //p是 g这个节点中左右最高的节点
        //n 是p 最右子树高度里面最高的
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) grand).tallerChild();
        //旋转方向的判断
        //L
        if (parent.isLeftChild()) {
            //判断子节点是否是左节点
            if (node.isLeftChild()) {
                //LL 对g进行右旋转
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                //LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            //R
            if (node.isLeftChild()) {
                //RL 先对parent进行右旋转
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                //RR 左旋转
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    /**
     * 统一的旋转 对应的参数
     */
    private void rotate(Node<E> r, // 子树的根节点 也就是之前的跟节点
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {
        //让d成为这个子树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        //处理ABC节点
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        //更新高度是让B的高度做出了修改
        updateHeight(b);

        //处理E,f,g
        //处理ABC节点
        f.left = e;
        if (e != null) {
            e.parent = b;
        }
        f.right = g;
        if (g != null) {
            g.parent = b;
        }
        updateHeight(f);

        //左边和右边都搞定了
        //处理b ,d ,f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);

        //d f b 主要的空的
    }

    /**
     * 左旋转 grand传进来对他进行左旋转
     */
    private void rotateLeft(Node<E> grand) {

        //怎么拿到p  现在传给我一个G 传给我那个就
        Node<E> parent = grand.right;

        //相当于T1
        Node<E> child = grand.right = parent.left;
        parent.left = grand;
        /**
         *  维护父节点的指向 以及高度
         */
        afterRotate(grand, parent, child);
    }

    /**
     * 进行右旋转
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> chind = parent.right;
        grand.left = chind;
        parent.right = grand;
        /**
         * 维护父节点 以及指向
         */
        afterRotate(grand, parent, chind);
    }

    /**
     * 抽取公共的代码
     * 旋转之后做的事情
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {

        //更新parent   t1的parent发生变化 p 与g的patent
        parent.parent = grand.parent;
        /**
         * 判断grand的结点
         */
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //grand是跟结点 让parent成为这个子树的根节点
            root = parent;
        }

        //更新child 的parent
        if (child != null) {
            child.parent = grand;
        }

        //更新grand 的parent
        grand.parent = parent;
        //更新高度  先更新矮的结点
        updateHeight(grand);
        updateHeight(parent);
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
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            //右子树的高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新自己节点的高度  每传一个节点都自动更新自己节点的高度
         */
        public void updateHeight() {
            //强制类型装换
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            //右子树的高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            //自己的高度 等于1 + 上  左子树 或者右子树中 最高的树
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 返回节点比较高的节点
         */
        public Node<E> tallerChild() {
            //强制类型装换
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
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

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
