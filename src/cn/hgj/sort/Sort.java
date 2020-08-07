package cn.hgj.sort;

import java.text.DecimalFormat;

/**
 * 描述:
 * 排序父类
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 13:38
 */
@SuppressWarnings("ALL")
public abstract class Sort implements Comparable<Sort> {
    protected Integer[] array;
    private int cmpCount;
    private int swapCount;


    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(Integer[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    protected abstract void sort();

    @Override
    public int compareTo(Sort o) {
        //当前时间减去另外一个时间
        int result = (int) (time - o.time);
        if (result != 0) {
            return result;
        }
        //如果姐夫哦一样
        result = cmpCount - o.cmpCount;
        if (result != 0) {
            return result;
        }

        return swapCount - o.swapCount;
    }

    /*
     * 返回值等于0，代表 array[i1] == array[i2]
     * 返回值小于0，代表 array[i1] < array[i2]
     * 返回值大于0，代表 array[i1] > array[i2]
     */
    protected int cmp(Integer i1, Integer i2) {
        cmpCount++;
        return array[i1] - array[i2];
    }

    /**
     * 比较元素
     */
    protected int cmpElements(Integer i1, Integer i2) {
        cmpCount++;
        return i1 - i2;
    }

    // 交换的方法
    protected void swap(Integer i1, Integer i2) {
        swapCount++;
        Integer tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
//        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
//                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) {
            return "" + number;
        }

        if (number < 100000000) {
            return fmt.format(number / 10000.0) + "万";
        }
        return fmt.format(number / 100000000.0) + "亿";
    }


}
