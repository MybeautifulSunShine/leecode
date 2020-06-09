package cn.leecode.set;

/**
 * @param <E>
 */
public interface Set<E> {
    /**
     * @return 长度
     */
    int size();

    /**
     * @return 为空
     */
    boolean isEmpty();

    /**
     * 清除
     */
    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
