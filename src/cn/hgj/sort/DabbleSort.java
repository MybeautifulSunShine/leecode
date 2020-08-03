package cn.hgj.sort;

import cn.hgj.sort.tools.Asserts;
import cn.hgj.sort.tools.Integers;

/**
 * 描述:
 * 冒泡算法
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-07-09 23:16
 */
public class DabbleSort {
    public static void main(String[] args) {
        Integer[] random = Integers.random(10, 1, 100);
        selectionSort(random);
        Asserts.test(Integers.isAscOrder(random));

    }

    /**
     * 冒泡排序v1
     */
    static void DabbleSort1() {
        int[] ints = {12, 22, 54, 10, 5, 25};
        for (int end = ints.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (ints[begin] < ints[begin - 1]) {
                    int tmp = ints[begin];
                    ints[begin] = ints[begin - 1];
                    ints[begin - 1] = tmp;
                }
            }
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "_");
        }
    }

    /**
     * 加上判断后边是否是排好序的
     */
    static void DabbleSort2() {
        int[] ints = {12, 22, 54, 10, 5, 25};
        for (int end = ints.length - 1; end > 0; end--) {
            boolean sored = true;
            for (int begin = 1; begin <= end; begin++) {
                if (ints[begin] < ints[begin - 1]) {
                    int tmp = ints[begin];
                    ints[begin] = ints[begin - 1];
                    ints[begin - 1] = tmp;
                    sored = false;
                }
            }
            if (sored) {
                break;
            }
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "_");
        }
    }

    /**
     * 选择排序 旋转排序交换在第二次比较里面
     *
     * @param integers
     */
    static void selectionSort(Integer[] integers) {
        for (int end = integers.length - 1; end > 0; end--) {
            Integer maxIndex = 0;
            for (int bengin = 1; bengin <= end; bengin++) {
                if (integers[maxIndex] <= integers[bengin]) {
                    maxIndex = bengin;
                }
            }
            //交换最后一个 把数最大的数据放到最后
            Integer tmp = integers[maxIndex];
            integers[maxIndex] = integers[end];
            integers[end] = tmp;
        }
    }

}
