package cn.leecode.linked;

@SuppressWarnings("unchecked")
public class MyArrayList<E> extends AbstractList<E> {
    /**
     * 所有的元素
     */
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capaticy) {
        capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        //清除缩容
        if (elements != null && elements.length > DEFAULT_CAPACITY) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }

    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) { // O(1)
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    @Override
    public E set(int index, E element) { // O(1)
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheckForAdd(index);
        //扩容
        ensureCapacity(size + 1);
        //挪动的范围是size-1 v1
      /*
        for (int i = size - 1; i >= index; i--) {
            //把原来的位置替换到 原来位置+1
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
        */

        //变换index
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }
    // size是数据规模

    /**
     * 删除index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheck(index);

        trim();
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }

    //进行减容
    private void trim() {
        //拿到长度
        int oldCapacity = elements.length;
        int newCapaCity = oldCapacity >> 1;
        //超过一半,或者容量本来很少进行缩容
        if (size >= (newCapaCity) || oldCapacity <= DEFAULT_CAPACITY) {
            return;
        }
        //进行缩容
        E[] newElements = (E[]) new Object[newCapaCity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 保证要有capacity的容量
     *
     * @param capacity 扩容的数量
     */
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

        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);

//			if (i != size - 1) {
//				string.append(", ");
//			}
        }
        string.append("]");
        return string.toString();
    }
}
