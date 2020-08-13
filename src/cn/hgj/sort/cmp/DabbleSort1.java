package cn.hgj.sort.cmp;

/**
 * 描述:
 * 冒泡排序v1
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 14:03
 */
public class DabbleSort1 <T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }

}
