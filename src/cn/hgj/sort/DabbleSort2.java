package cn.hgj.sort;

/**
 * 描述:
 * 冒泡排序v2
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 14:12
 */
public class DabbleSort2 extends Sort {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin < end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
