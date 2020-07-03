package cn.leecode.heap;

import cn.leecode.heap.printer.BinaryTrees;

import java.util.PriorityQueue;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-29 14:50
 */
public class HeapMain {
    static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
//        heap.remove();
//        BinaryTrees.println(heap);

        System.out.println(heap.replace(70));
        BinaryTrees.println(heap);
    }

    static void test2() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(data);
        BinaryTrees.println(heap);

        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heap);
    }

    static void test3() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, (o1, o2) -> o1 - o2);
        BinaryTrees.println(heap);
    }

    static void test4() {
        // 新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(((o1, o2) -> o2 - o1));
        /**
         * 找出最大的前k个数字
         */
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0; i < data.length; i++) {
            //把前几个数加到小顶堆里面
            if (heap.size < k) {
                heap.add(data[i]);
            } else if (data[i] > heap.remove()) {
                // 如果是第k + 1个数，并且大于堆顶元素
                heap.replace(data[i]);
            }
        }
    }

    static void test5() {
        /**
         * 优先级队列的出队与入队
         */
        /**
         * PriorityQueue<Person> queue = new PriorityQueue<>();
         * 		queue.enQueue(new Person("Jack", 2));
         * 		queue.enQueue(new Person("Rose", 10));
         * 		queue.enQueue(new Person("Jake", 5));
         * 		queue.enQueue(new Person("James", 15));
         *
         * 		while (!queue.isEmpty()) {
         * 			System.out.println(queue.deQueue());
         *                }
         */
    }

    public static void main(String[] args) {
        PriorityQueue<Object> objects = new PriorityQueue<>();
    }
}
