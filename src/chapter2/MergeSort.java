package chapter2;

import java.util.Arrays;

/**
 * @author kissx on 2017/9/17.
 *         归并排序
 */
public class MergeSort {

    private static void mergeSortFunction(int[] array) {
        mergeSort(array, 0, array.length-1);
    }

    private static void mergeSort(int[] array, int s, int e) {
        if (s != e) {
            int mid = (s + e) / 2;
            mergeSort(array, s, mid);
            mergeSort(array, mid + 1, e);
            merge(array, s, mid, e);
        }
    }

    private static void merge(int[] array, int s, int mid, int e) {
        int[] a1 = Arrays.copyOfRange(array, s, mid + 1);
        int[] a2 = Arrays.copyOfRange(array, mid + 1, e + 1);
        int j = 0;
        int k = 0;
        for (int i = s; i <= e; i++) {
            if (j >= a1.length) {
                array[i] = a2[k];
                ++k;
                continue;
            }
            if (k >= a2.length) {
                array[i] = a1[j];
                ++j;
                continue;
            }
            if (a1[j] < a2[k]) {
                array[i] = a1[j];
                ++j;
            } else {
                array[i] = a2[k];
                ++k;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 18, 6, 4, 1, 9, 7};
        System.out.println("【原数组】：" + Arrays.toString(array));
        mergeSortFunction(array);
        System.out.println("【排序后】：" + Arrays.toString(array));
    }

}
