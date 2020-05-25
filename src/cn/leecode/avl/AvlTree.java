package cn.leecode.avl;

import cn.leecode.refactorBinaty.BST;

import java.util.Comparator;

/**
 * 描述:
 * Avl树
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-25 13:55
 */
public class AvlTree<E> extends BST<E> {
    /**
     * 为了能默认添加比较器 重写父类的构造函数
     */
    public AvlTree() {
        this(null);
    }

    public AvlTree(Comparator<E> comparator) {
        super(comparator);
    }

}
