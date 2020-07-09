package cn.sort.bubblesort;

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
        int[] ints = {12, 22, 54, 10, 5, 25};
        for (int end = ints.length - 1; end > 1; end--) {
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
}
