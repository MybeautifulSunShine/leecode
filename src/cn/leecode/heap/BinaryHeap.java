package cn.leecode.heap;

import cn.leecode.heap.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 描述:
 * 二叉堆(最大堆)
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-28 18:26
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    //默认的元素
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparable) {
        super(comparable);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }


    public BinaryHeap(E[] elements) {
        this.elements = elements;
    }

    private void heapify() {
        // 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}
        /**
         * 自下而上的下虑
         */
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     * 1 把最后一个元素覆盖掉第一个的位置
     * 2 再把最后一个元素删除掉
     * 3 在做判断
     * 4 在那边挑 ?子节点中最大的 一个结点交换
     */
    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;

  /*   v1
        E root = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;*/
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return root;
    }

    /**
     * 替换堆顶元素
     *
     * @param element 要替换的元素
     * @return 返回被的元素
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 让index位置的元素上滤
     *
     * @param index
     */
    private void siftUp(int index) {
        /*  E element = elements[index];

         *//**
         * 不是第一个位置
         *//*
        while (index > 0) {
            //找到父节点 根据公式得出
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) {
                return;
            }
            //将父元素的地址写到index 的位置
            E tmp = elements[index];
            elements[index] = elements[parentIndex];
            elements[parentIndex] = tmp;
            //交换pindex的位置
            index = parentIndex;
        }*/

        E e = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E p = elements[pIndex];
            if (compare(e, p) <= 0) {
                break;
            }
            //将父元素存储在index的位置
            elements[index] = p;
            //重新赋值index
            index = pIndex;
        }
        elements[index] = e;
    }

    /**
     * 让index结点的元素下滤
     *
     * @param index 位置对应的子节点
     */
    private void siftDown(int index) {
        E element = elements[index];
        //必须要有子节点 也就是index的位置必须要有子节点
        //index < 第一个叶子结点的索引 (也就是说下虑的东西必须要有叶子节点)
        // 第一个叶子结点的索引 == 非叶子结点的数量 也就是小于非叶子节点的数量
        int half = size >> 1;
        while (index < half) {
            //index 的结点有两种情况
            //1 只有左子节点
            //2 同时有左右子节点
            //默认左子节点的索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            /**
             * 找到右子节点 存在
             */
            int rightIndex = childIndex + 1;
            //右边要比左边大
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }
            /**
             * 找到最大的子节点 进行比较
             */
            if (compare(element, child) > 0) {
                break;
            }
            // 将子节点存放到index的位置
            elements[index] = child;
            //重新设置index
            index = childIndex;
            //106121575
        }
        elements[index] = element;
    }

    /**
     * 理解
     *
     * @param index
     */
    private void siftDown2(int index) {
        //index 必须有子节点
        //参考公式
        E node = elements[index];
        int half = index >> 1;
        while (index < half) {
            //获取默认左节点的索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            //获取到右边的内容
            int rightIndex = childIndex + 1;

            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }

            if (compare(node, child) > 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = node;

    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
