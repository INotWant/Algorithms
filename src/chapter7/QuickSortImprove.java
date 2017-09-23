package chapter7;

import java.util.Random;

/**
 * @author kissx on 2017/9/23.
 *         快速排序改进版
 */
public class QuickSortImprove {

    public static void quickSortImproveFunction(int[] array) {
        quickSortImprove(array, 0, array.length - 1);
    }

    public static void quickSortImprove(int[] array, int start, int end) {
        if (end > start) {
            int pos = partitionImprove(array, start, end);
            quickSortImprove(array, start, pos - 1);
            quickSortImprove(array, pos + 1, end);
        }
    }

    public static int partitionImprove(int[] array, int start, int end) {
        Random random = new Random();
        int pos = random.nextInt(end - start + 1) + start;
        int temp = array[pos];
        array[pos] = array[end];
        array[end] = temp;
        return QuickSort.partition(array, start, end);
    }

    public static void main(String[] args) {
        long sTime = System.currentTimeMillis();
        int[] array = new int[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = i + 1;
        }
        quickSortImproveFunction(array);
        long eTime = System.currentTimeMillis();
        System.out.println("总时间：" + (eTime - sTime));
    }

}
