package cn.hgj.sort.cmp;

/**
 * 描述:
 * 堆排序
 *
 * @author HGJ
 * @version 1.0
 * @create 2020-08-07 16:01
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;

    @Override
    protected void sort() {
        //批量建堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            //自上而写的上虑
            siftDown(i);
        }
        while (heapSize > 1) {
            //交换顶元素
            swap(0, --heapSize);
            //对0的位置进行下虑恢复堆的性质
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        T elements = array[index];

        //找到第一个子节点的位置
        int half = heapSize >> 1;
        //index必须是非叶子节点
        while (index < half) {
            //找到左子节点的位置
            int chindIndex = (index << 1) + 1;
            T chind = array[chindIndex];
            //找到右子节点进行比较
            int rightIndex = chindIndex + 1;
            //比较的东西前后要注意
            if (rightIndex < heapSize && cmp(array[rightIndex], chind) > 0) {
                chindIndex = rightIndex;
                chind = array[chindIndex];
            }

            if (cmp(elements, chind) >= 0) {
                break;
            }
            //交换索引与元素
            array[index] = chind;
            index = chindIndex;

        }
        array[index] = elements;
    }


}
