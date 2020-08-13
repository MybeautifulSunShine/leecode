package cn.hgj.sort.cmp;

/**
 * 描述:
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-13 14:55
 */
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            //不能直接使用begin 记录begin的位置
            int cur = begin;
            //与前一个位置进行比较
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                //比较完一个在比较上一个
                cur--;
            }
        }
    }
}
