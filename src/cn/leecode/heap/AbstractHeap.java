package cn.leecode.heap;

import java.util.Comparator;

/**
 * 描述:
 * 抽取公共的东西
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-28 23:08
 */
public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
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

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2)
                : ((Comparable<E>) e1).compareTo(e2);
    }
}
