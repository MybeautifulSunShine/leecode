package cn.hgj.sort.cmp;

/**
 * 描述:
 * 在插入排序中旋转二分查找
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-13 17:08
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序数组的区间范围是 [0, index)
     *
     * @param index
     * @return
     */
    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    /**
     * 将source位置的元素插入到dest位置
     *
     * @param source index的位置
     */
    private void insert(int source, int dest) {
        T v = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;
    }
}
