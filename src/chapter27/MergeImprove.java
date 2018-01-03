package chapter27;

import java.util.Arrays;

/**
 * @author kissx on 2017/2/12.
 */
public class MergeImprove implements MergeSort {

    private static int count = 0;

    // TODO you own code and other people's code, because thinking isn't clear.
    // TODO One thinking: take a correct element into a right position util the end of recursion.

    // TODO for the one time call to merge(), saveArray's relevant position is assigned only one time.
    // TODO however, MergeSort will call merge() multiple times, so if don't sync every time, it's going to have a thread safe problem.

    /**
     * merge a1 and a2, a1 and a2 is already sorted array
     *
     * @param s1    the start of a1
     * @param e1    the end of a1
     * @param s2    the start of a2
     * @param e2    the end of a2
     * @param array wait for merging array
     */
    private void merge(int s1, int e1, int s2, int e2, int[] array, int start, int[] saveArray) {
        // 1, make a1 is bigger than a2
        if (e2 - s2 > e1 - s1) {
            int temp = s1;
            s1 = s2;
            s2 = temp;
            temp = e1;
            e1 = e2;
            e2 = temp;
        }
        // 0, solve little problem
        if (e1 - s1 == -1)
            return;
        // 2, get median of the a1
        int medianPos = s1 + (e1 - s1) / 2;
        int median = array[medianPos];
        // 3, get pos and redistribute
        int pos = getPos(median, s2, e2, array);

//        int[] newArray = new int[array.length];
//        copy(array, newArray, s1, medianPos - 1, s1);
//        copy(array, newArray, s2, pos, medianPos);10000
//        int newMedianPos = medianPos + pos - s2 + 1;
//        newArray[newMedianPos] = median;
//        copy(array, newArray, medianPos + 1, e1, newMedianPos + 1);
//        copy(array, newArray, pos + 1, e2, newMedianPos + 1 + e1 - medianPos);
        // 4, copy array and update array
//        copy(array, newArray, 0, s1 - 1, 0);
//        copy(array, newArray, e2, array.length - 1, e2);
//        array = newArray;
        // 5, recursion
//        merge(s1, medianPos - 1, medianPos, newMedianPos - 1, array);
//        merge(newMedianPos + 1, newMedianPos + 1 + e1 - medianPos, newMedianPos + e1 - medianPos + 2, e2, array);

        int newMedianPos = start + (medianPos - s1) + (pos - s2);
        saveArray[newMedianPos] = array[medianPos];
        merge(s1, medianPos - 1, s2, pos - 1, array, start, saveArray);
        merge(medianPos + 1, e1, pos, e2, array, newMedianPos + 1, saveArray);
    }


    /*
    private static void copy(int[] sArray, int[] dArray, int from, int to, int start) {
        for (int i = from; i <= to; i++) {
            dArray[start++] = sArray[i];
        }
    }
    // */

    private static int getPos(int num, int start, int end, int[] array) {
        while (end >= start) {
            int mid = (end - start) / 2 + start;
            if (array[mid] >= num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    @Override
    public int[] mergeSortFunction(int[] array) {
        int[] saveArray = new int[array.length];
        mergeSort(array, 0, array.length - 1, saveArray);
        return saveArray;
    }

    private void mergeSort(int[] array, int s, int e, int[] saveArray) {
        if (s != e) {
            int mid = (s + e) / 2;
            int[] t = new int[array.length];
            mergeSort(array, s, mid, t);
            mergeSort(array, mid + 1, e, t);
            merge(s, mid, mid + 1, e, t, s, saveArray);
        } else
            saveArray[s] = array[s];
    }

    public static void main(String[] args) {
        int[] array = {2, 9, 1, 8, 2, 10, 18, 21, 12, 5, 7, 4, 3};
        MergeImprove mergeImprove = new MergeImprove();
        int[] saveArray = mergeImprove.mergeSortFunction(array);
        System.out.println(Arrays.toString(saveArray));
    }

}
