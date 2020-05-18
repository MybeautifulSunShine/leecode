package cn.leecode.printer;

import java.lang.*;
import java.util.*;
import java.util.Comparator;

/**
 * 描述:
 * 二叉搜索树
 * 1 E 必须具备可比较性
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 18:52
 */
public class BinarySearchTree<E> {
    private int size;

    /**
     * 根结点
     */
    private Node<E> root;

    /**
     * 在提供一个构造方法不需要写构造器
     */
    public BinarySearchTree() {
        this(null);
    }


    /**
     * 传入默认的比较器
     */
    private java.util.Comparator<E> comparator;

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
//        if (comparable != null) {
//            return comparable.(e1, e2);
//        }
        //parent1
//        return comparable.compare(e1, e2);
        /**
         * 如果没有比较器 同时自己又不能比 那么强制装换成比比较器接口
         * 如果装换出现了问题 那就说明不能比,传的参数是错误的
         */
        return ((Comparable<E>) e1).compareTo(e2);
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

}
