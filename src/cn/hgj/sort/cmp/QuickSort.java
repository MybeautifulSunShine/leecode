package cn.hgj.sort.cmp;

/**
 * 描述:
 * 快速排序
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-17 11:20
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        // 确定轴点位置 O(n)
        int mid = pivotIndex(begin, end);
        // 对子序列进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 构造出 [begin, end) 范围的轴点元素
     *
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素跟begin位置进行交换
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        //备份轴点元素
        T pivot = array[begin];
        //end 指向最后位置之前是左臂右开
        end--;
        while (begin < end) {
            //从右向左扫描
            // 右边元素 > 轴点元素
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                } else {
                    // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                //从左向右扫描
                //左边元素小于右边元素
//                左边元素 < 轴点元素
                if (cmp(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    //左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }

        }
        // 将轴点元素放入最终的位置
        array[begin] = pivot;
        // 返回轴点元素的位置
        return begin;
    }
}
