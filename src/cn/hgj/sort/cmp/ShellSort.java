package cn.hgj.sort.cmp;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述:
 * 希尔排序
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-17 14:05
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    //步长序列


    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 分成step列进行排序
     */
    private void sort(int step) {
        //col : 第几列
        for (int col = 0; col < step; col++) {
            //对0 到array.length 范围的元素进行插入排序
            for (int begin = col + step; begin < array.length; begin += step) {
                //不能直接使用begin 记录begin的位置
                int cur = begin;
                //与前一个位置进行比较
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    //比较完一个在比较上一个
                    cur -= step;
                }
            }
        }
    }

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = (List<Integer>) new ArrayList<Integer>();
        int step = array.length;
        //都是2 的倍数
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}
