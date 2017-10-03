package chapter9;

import chapter7.QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kissx on 2017/10/2.
 *         <p>
 *         期望为线性时间的选择算法（基于快排思想）
 */
public class SelectI1 {

    /**
     * 快排的划分
     */
    private static int partition(int[] array, int start, int end) {
        int i = new Random().nextInt(end - start + 1) + start;
        int temp = array[end];
        array[end] = array[i];
        array[i] = temp;
        i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if (array[j] <= array[end]) {
                ++i;
                if (i != j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        temp = array[end];
        array[end] = array[++i];
        array[i] = temp;
        return i;
    }

    private static Integer select(int array[], int start, int end, int i) {
        int pos = partition(array, start, end);
        if (pos + 1 == i)
            return array[pos];
        else if (pos + 1 < i)
            return select(array, pos + 1, end, i);
        else
            return select(array, start, pos - 1, i);
    }

    public static Integer select(int[] array, int i) {
        return select(array, 0, array.length - 1, i);
    }

    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int result = select(array, 10);
        System.out.println("第 10 小的元素为：" + result);
        QuickSort.quickSortFunction(array);
        System.out.println("[原数组] " + Arrays.toString(array));
    }

}
