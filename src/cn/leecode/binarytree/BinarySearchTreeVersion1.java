package cn.leecode.binarytree;

import cn.leecode.binarytree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
public class BinarySearchTreeVersion1<E> implements BinaryTreeInfo {
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
    private Comparator<E> comparator;

    /**
     * 在提供一个构造方法不需要写构造器
     */
    public BinarySearchTreeVersion1() {
        this(null);
    }


    public BinarySearchTreeVersion1(Comparator<E> comparator) {
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

    // 清空所有元素
    public void clear() {

    }

    /**
     * // 添加元素
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
// 删除元素

    public void remove(E element) {

    }

    // 是否包含某元素
    public boolean contains(E element) {
        return false;
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

    /**
     * 前序遍历
     */
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

    /**
     * 中序遍历 (root节点是最中间的) 出来的结果是升序或者降序的
     */
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

    /**
     * 后续遍历   父节点最后遍历
     */
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

    /**
     * 层序遍历
     */
    private void levelOrderTraversal() {
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
