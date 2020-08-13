package cn.hgj.sort.cmp;

/**
 * 描述:
 * 选择排序
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 13:38
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[maxIndex] <= array[end]) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            //交换最后一个 把数最大的数据放到最后
            swap(maxIndex, end);
        }
    }
}
