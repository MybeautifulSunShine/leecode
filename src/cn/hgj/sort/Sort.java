package cn.hgj.sort;

/**
 * 描述:
 * 排序父类
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-03 13:38
 */
@SuppressWarnings("ALL")
public abstract class Sort {
    protected Integer[] array;
    private Integer cmpCount;
    private Integer swapCount;

    public void sort(Integer[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;
        sort();
    }

    protected abstract void sort();

    /*
     * 返回值等于0，代表 array[i1] == array[i2]
     * 返回值小于0，代表 array[i1] < array[i2]
     * 返回值大于0，代表 array[i1] > array[i2]
     */
    protected int cmp(Integer i1, Integer i2) {
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

}
