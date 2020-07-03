package cn.leecode.binarytree;

import cn.leecode.binarytree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 描述:
 * 二叉搜索树
 * 限制改 接口的类型
 * version 1 BinarySearchTree<E extend Comparable >
 * 1 E 必须具备可比较性
 * version 2 实现了打印的接口 并且告诉相关方法
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 18:52
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    /**
     * 大小长度
     */
    private int size;

    /**
     * 根结点
     */
    private Node<E> root;


    /**
     * 传入默认的比较器  提供一个比较器
     */
    private java.util.Comparator<E> comparator;

    /**
     * 在提供一个构造方法不需要写构造器
     */
    public BinarySearchTree() {
        this(null);
    }


    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // 元素的数量
    public int size() {
        return 0;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 添加元素
     */
    public void add(E element) {
        //先确定在那个位置
        elementNotNullCheck(element);
        //添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        //如果能来到这里说明 不是空的  跟节点不断的进行比较
        //1 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        // 2 创建新的节点 >0 代表e1大于e2
        while (node != null) {
            cmp = compare(element, node.element);
            //保存
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                //相等 把传进来的值把覆盖掉之前的元素
                //自定义对象 这样 就可以替换掉地址值
                node.element = element;
                return;
            }
        }
        // 3 用left 或者right 指向
        //看看插入到父节点的那个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            //插入到父节点的右边
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 删除元素
     *
     * @param element
     */

    public void remove(E element) {
        //先根据元素找到这个节点
        //在根据remove方法删除掉
        remove(node(element));
    }

    /**
     * 删除的真正方法
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        //判断度的大小查看度为几
        //度为2   找到前序 和后继 删除对应的节点
        if (node.hasTwoChildren()) {
            //找到前驱或者后继
            Node<E> s = successor(node);
//            Node<E> s = predeessor(node);
            //用后继节点的值覆盖度为2节点的值
            node.element = s.element;
            //删除后继节点  指向节点
            node = s;
        }
        //删除node 度为0 或者 度为 1 节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //node 是度为1的节点 用左边或者右边取代要删除的节点
            //更改parent
            replacement.parent = node.parent;
            if (node.parent == null) {
                //如果删除的是根结点的话 根结点直接指向要删除的子节点
                root = replacement;
                //parent 的left或right的指向  取决于 要删除的节点是 父节点的左边还是右边
                //如果删的节点是右边指向
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            //node是叶子节点 并且是根结点
            root = null;
        } else {
            //是叶子节点 但不是根结点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }

    }

    /**
     * 找到节点的方法
     *
     * @param element
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            //相等
            if (cmp == 0) {
                return node;
            }
            //意味着要找的元素比当前大 就去右边去找
            if (cmp > 0) {
                node = node.right;
            } else {
                //去左子树里面去找
                node = node.left;
            }
        }
        //不存在返回null
        return null;
    }


    /**
     * 是否包含某元素
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 通过层序遍历的方式来获取树的高度
     * 规律 : 每一层的最后一个节点出队的时候  队列里面保存的是下一节点的数量
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        // 树的高度
        int height = 0;
        // 存储着每一层的元素数量  默认root的数量为0
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        //一进来就把根结点入队
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            //没出队一个元素则里面的数量-1
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
            // 意味着即将要访问下一层
            if (levelSize == 0) {
                //赋值
                levelSize = queue.size();
                //访问完一层高度就自动++
                height++;
            }
        }

        return height;
    }

    /**
     * 判断是不是完全的二叉树
     * 树为null 返回false
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        /**
         * 是不是叶子节点
         */
        boolean leaf = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                //后面遍历的节点必须是叶子节点
                leaf = true;
            }

        }
        return leaf;
    }

    /**
     * 计算节点的高度 version1
     *
     * @return 高度
     */
    public int height1() {
        return height1(root);
    }

    /**
     * 获取一个 节点的高度 递归的方式
     */
    private int height1(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height1(node.right), height1(node.left));
    }


    /**
     * 实现默认的打印的方法
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    /**
     * 调用打印的方法
     */
    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }

        toString(node.left, sb, prefix + "L---");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb, prefix + "R---");
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {

        /**
         * 实现接口告诉你应该怎么实现
         * 传入不同的比较器 由外秒的实现类告诉你怎么工作
         如果传入比较器 那么优先用比较器
         */
        //version 2
//        if (comparable != null) {
//            return comparable.(e1, e2);
//        }
        //version1
//        return comparable.compare(e1, e2);
        /**
         * 如果没有比较器 同时自己又不能比 那么强制装换成比比较器接口
         * 如果装换出现了问题 那就说明不能比,传的参数是错误的
         */
        return ((java.lang.Comparable<E>) e1).compareTo(e2);
    }
    /*
     *//**
     * 前序遍历  version1
     *//*
    public void preorderTraversal() {
        //从根结点开始
        preorderTraversal(root);
    }

    public void preorderTraversal(Node<E> node) {
        //访问这个节点
        if (node.element == null) {
            return;
        }
        //中节点
        System.out.println(node.element);
        //访问左节点
        preorderTraversal(node.left);
        //访问右节点
        preorderTraversal(node.right);
    }

    *//**
     * 中序遍历 (root节点是最中间的) 出来的结果是升序或者降序的
     *//*
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    *//**
     * 后续遍历   父节点最后遍历
     *//*
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    public void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    *//**
     * 层序遍历
     *//*
    public void levelOrderTraversal() {
        if (null == root) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        //讲根节点入队
        queue.offer(root);
        //只要队列部位空 那就取它的头结点
        while (!queue.isEmpty()) {
            //头结点出队
            Node<E> poll = queue.poll();
            //访问
            System.out.println(poll.element);
            //如果左节点不等于空就让它入队
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            //让右节点入队
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }*/

    /**
     * version2 把之前的方法都做成通过接口来访问的 而不是直接调用 ,不把参数暴露给外边
     * 前序遍历
     */

    public void preorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历version2
     *
     * @param visitor
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        /**
         * 第一个判断是未了不进入递归调用里面
         */
        if (node == null || visitor.stop) {
            return;
        }

        inorder(node.left, visitor);
        /**
         * 第二个判断是为了禁止自己的东西
         */
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后续遍历
     *
     * @param visitor 遍历器
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }


    /**
     * 判断参数是否为Null
     *
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 前序结点 : 中序遍历中,求某一个结点的前一个结点是什么
     * 只要是暴露结点的操作都是私有的方法
     * 条件
     * 左,右 右
     * node.left!=null
     * 终止条件right 为 null
     * 左子树等于 null 但是parent!=null
     * 某一个parent的right 就是它的前驱
     */
    private Node<E> predeessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        //发现左结点!= null 不断的往右寻找
        //前驱结点在左子树当中
        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //从父节点 祖父节点中寻找前驱节点 没有前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //循环结束  1 paret == null
        //node =node.parent.right
        return node.parent;
    }

    /**
     * 非递归的遍历
     */
    private void preorder2(Visitor<E> visitor) {
        if (visitor.stop) {
            return;
        }
        Node<E> node = root;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while (stack.isEmpty()) {
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }

    /**
     * 后续节点 ,求某个节点的后一个节点
     * 逻辑跟上面的一样 但是确实相反的
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        /**
         * 提取右节点
         */
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //从父节点 祖父节点中寻找前驱节点 没有前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    /**
     * 要先遍历所有的元素给我 Visitor 这个接口 由visitor告诉你应该怎么做
     * 因为里面的参数是element是不可让调用者知道的
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            /**
             * 真正的使用结点的内用 根据判断值返回内容
             */
            if (visitor.visit(node.element)) {
                return;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 内部接口 访问器
     */
   /* public static interface Visitor<E> {
        void visit(E element);
    }
   */
    public static abstract class Visitor<E> {
        //判断是否是继续遍历
        private boolean stop;

        /**
         * @param element 里面的元素
         * @return 如果接口返回true就说明是遍历结束
         */
        abstract boolean visit(E element);
    }

    /**
     * 维护的节点 一个Node代表一个节点
     */
    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 判断是不是叶子节点
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否是有两个子节点
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    /**
     * 打印方法的实现
     *
     * @return
     */

    @Override
    public Object root() {
        return root;
    }

    /**
     * 告诉左节点
     *
     * @param node
     * @return
     */
    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    /**
     * 告诉右节点
     *
     * @param node
     * @return
     */
    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        /**
         * 打印的时候打印出它的父节点是什么
         */
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
}
