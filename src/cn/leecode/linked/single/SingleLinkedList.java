package cn.leecode.linked.single;

import cn.leecode.linked.AbstractList;

/**
 * 描述:
 * 链表数据结构
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-17 16:06
 */
public class SingleLinkedList<E> extends AbstractList<E> {

    //内部类,只用于LinkedList

    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.next = next;
            this.element = element;
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        //取到上一个的东西
        Node<E> node = node(index);
        //赋值
        E oldNode = node.element;
        node.element = element;
        return oldNode;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index != 0) {
            //1找到index上一个的位置 的元素,
            Node<E> prve = node(index - 1);
            //2 链表指向
            prve.next = new Node<>(element, prve.next);
            //如果是0 做操作
        } else {
            first = new Node<>(element, first);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        //检查索引
        rangeCheck(index);

        Node<E> node = first;
        if (index != 0) {
            //获取到上一个参数
            Node<E> prve = node(index - 1);
            node = prve.next;
            prve.next = node.next;
        } else {
            //如果index 是 0
            first = first.next;
        }
        size--;
        return node.element;
    }


    /**
     * 获取到对应的Index
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对于的节点对象
     *
     * @param index 对应索引的位置
     * @return
     */
    private Node<E> node(int index) {
        //检查索引
        rangeCheck(index);
        //获取到第一个的节点
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            //一路获取到所对应的数据
            node = node.next;
        }
        return node;
    }

}
