package cn.hgj.sort.cmp;

/**
 * 描述:
 * 归并排序\
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-17 10:42
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对 begin和 end的范围数据进行归并排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 合并的方法
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     * 左闭右开
     */
    private void merge(int begin, int mid, int end) {
        //le  左闭右开   le 就是左边数组的长度
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        for (int i = li; i < le; i++) {
            //因为传入的数据是 begin的数组并不是0 所有是begin+i
            leftArray[i] = array[begin + i];
        }
        //如果左边没有结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }

    }

}
