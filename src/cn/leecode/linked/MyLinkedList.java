package cn.leecode.linked;

/**
 * 描述:
 * 链表数据结构
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-17 16:06
 */
public class MyLinkedList<E> extends AbstractList<E> {

    //内部类,只用于LinkedList

    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;


        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }
            return sb.toString();
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
        //size = index =0
        if (index == size) {
            //获取到之前的last
            Node<E> oldLast = last;
            //往最后面添加元素 并且
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) {
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            //找到下一个
            Node<E> next = node(index);
            //1找到index上一个的位置 的元素,
            Node<E> prve = next.prev;
            //2 链表指向
            Node<E> node = new Node<>(prve, element, next);
            //重新指向
            next.prev = node;
            //如果是0 做操作  index == 0情况
            if (prve == null) {
                first = node;
            } else {
                prve.next = node;
            }
        }
        size++;
    }

    /**
     * 删除内容
     *
     * @param index 索引
     * @return 返回删除的东西
     */
    @Override
    public E remove(int index) {
        //检查索引
        rangeCheck(index);
        // [null_11_66, 11_66_22, 66_22_33, 22_33_44, 33_44_77, 44_77_null]
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        //判断是否是第一个  index  == 0
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        //判断是否是最后一个 index= (size -1)
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
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
        if (element != null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = first;
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
        Node<E> node ;
        if (index < (size >> 1)) {
            node = first;
            //获取到第一个的节点
            for (int i = 0; i < index; i++) {
                //一路获取到所对应的数据
                node = node.next;
            }
            return node;
        } else {
            node = last;
            //获取到最后一个
            for (int i = size - 1; i > index; i--) {
                //一路获取到所对应的数据
                node = node.prev;
            }
            return node;
        }

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
