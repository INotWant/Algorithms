package chapter27;

import java.util.Arrays;

/**
 * @author kissx on 2017/2/12.
 */
public class MergeSortSingle implements MergeSort {

    @Override
    public int[] mergeSortFunction(int[] array) {
        int[] myArray = Arrays.copyOf(array, array.length);
        mergeSort(myArray, 0, myArray.length - 1);
        return myArray;
    }

    private void mergeSort(int[] array, int s, int e) {
        if (s != e) {
            int mid = (s + e) / 2;
            mergeSort(array, s, mid);
            mergeSort(array, mid + 1, e);
            merge(array, s, mid, e);
        }
    }

    private void merge(int[] array, int s, int mid, int e) {
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

}
