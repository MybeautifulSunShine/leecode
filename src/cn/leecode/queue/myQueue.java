package cn.leecode.queue;

import cn.leecode.linked.MyLinkedList;
import cn.leecode.linked.MyList;

/**
 * 描述:
 * 队列的数据结构
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-06 10:51
 */
public class myQueue<E> {
    private MyList<E> list = new MyLinkedList<E>();

    //长度
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    //清除
    public void clear() {
        list.clear();
    }

    //从尾部入队
    public void enQueueRear(E element) {
        list.add(element);
    }

    //从头部出队
    public E deQueueFront() {
        return list.remove(0);
    }

    //从头部入队
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    //从尾部出队
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    //获取头部
    public E front() {
        return list.get(0);
    }

    //获取尾部
    public E rear() {
        return list.get(list.size() - 1);
    }
}
