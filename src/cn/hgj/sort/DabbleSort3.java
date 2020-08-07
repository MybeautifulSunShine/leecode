package cn.hgj.sort;

/**
 * 描述:
 * 排序算法3存一个上次交换的位置
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-07 15:16
 */
public class DabbleSort3 extends Sort {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
