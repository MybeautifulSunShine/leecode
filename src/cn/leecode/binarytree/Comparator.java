package cn.leecode.binarytree;

/**
 * 新建一个比较器
 *
 * @param <E>
 */
public interface Comparator<E> {
    int compare(E e1,E e2);
}
