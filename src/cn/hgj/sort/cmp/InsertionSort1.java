package cn.hgj.sort.cmp;

/**
 * 描述:
 * 思路1 不需要每次都替换
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-13 16:58
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            //给一个临时变量 用来存储要替换的变量
            T v = array[cur];
            //比较与交换  批量交换
            while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            //插入并且替换元素
            array[cur] = v;
        }
    }
}
