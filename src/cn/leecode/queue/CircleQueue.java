package cn.leecode.queue;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
    //队头的
    private int front;
    //长度
    private int size;
    //元素
    private E[] elements;
    //默认的数据长度
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    public void enQueue(E element) {
        //动态扩容
        ensureCapacity(size + 1);
        //第一版 应该是size的长度加上
//        elements[(front + size) % elements.length] = element;
        elements[index(size)] = element;
        size++;
    }

    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        //第一版
//        front = (front + 1) % elements.length;
        front = index(1);
        size--;
        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        //容量
        string.append("capcacity=").append(elements.length)
                //长度
                .append(" size=").append(size)
                //头部
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    private int index(int index) {
        //传入之前的索引给我,返回循环队列上面的索引
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    /**
     * 保证要有capacity的容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            //把旧的数组的下标赋值给新的
//            newElements[i] = elements[(i + front) % elements.length];
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        // 重置front
        front = 0;
    }
}
