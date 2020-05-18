package cn.leecode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 描述:
 * 栈
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-04-28 16:46
 */
public class MyStack<E> {

    private List<E> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }


    public E pop() {
        return list.remove(list.size() - 1);
    }


    public E top() {
        return list.get(list.size() - 1);
    }
}
