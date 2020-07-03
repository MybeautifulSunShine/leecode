package cn.leecode.queue;

import cn.leecode.heap.BinaryHeap;

import java.util.Comparator;

/**
 * 描述:
 * 优先级队列
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-30 13:11
 */
public class PriorityQueue<E> {
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        /**
         * 出队就是删除
         */
        return heap.remove();
    }

    public E front() {
        /**
         * 获取到元素就是
         */
        return heap.get();
    }
}
