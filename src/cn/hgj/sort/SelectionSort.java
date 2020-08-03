package cn.hgj.sort;

/**
 * 描述:
 * 选择排序
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 13:38
 */
public class SelectionSort extends Sort {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            Integer maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[maxIndex] <= array[end]) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = end;
                }
            }
            //交换最后一个 把数最大的数据放到最后
            swap(maxIndex, end);
        }
    }
}
