package cn.leecode.heap;

import java.util.Comparator;

/**
 * 描述:
 * 二叉堆(最大堆)
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-28 18:26
 */
public class BinaryHeap<E> implements Heap<E> {
    private E[] elements;
    private int size;
    //默认的元素
    private static final int DEFAULT_CAPACITY = 10;
    private Comparator<E> comparable;

    public BinaryHeap(Comparator<E> comparable) {
        this.comparable = comparable;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        ensureCapacity(size + 1);
        elementNotNullCheck(element);

        elements[size++] = element;

    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(Object element) {
        return null;
    }

    public int compare(E el, E e2) {
        return comparable != null ? comparable.compare(el, e2) : ((Comparable<E>) el).compareTo(e2);
    }

    /**
     * 让index位置的元素上滤
     *
     * @param index
     */
    private void siftUp(int index) {
        E e = elements[index];
        /**
         * 不是第一个位置
         */
        while (index > 0) {
            E p = elements[size >> 1];

        }


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


}
