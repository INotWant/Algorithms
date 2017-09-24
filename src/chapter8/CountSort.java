package chapter8;

import java.util.Arrays;

/**
 * @author kissx on 2017/9/24.
 *         <p>
 *         计数排序
 */
public class CountSort {

    public static int[] countSort(int[] array) {
        if (array != null) {
            int[] arrayResult = new int[array.length];
            if (array.length > 0) {
                int max = array[0];
                for (int i = 1; i < array.length; i++) {
                    if (array[i] > max)
                        max = array[i];
                }
                int[] kArray = new int[max + 1];
                for (int anArray : array) {
                    kArray[anArray]++;
                }
                for (int i = 1; i < kArray.length; i++) {
                    kArray[i] = kArray[i] + kArray[i - 1];
                }
                for (int i = array.length - 1; i >= 0; i--) {
                    arrayResult[--kArray[array[i]]] = array[i];
                }
            }
            return arrayResult;
        }
        return null;
    }


    public static void main(String[] args) {
        int[] array = new int[]{0, 2, 1, 20, 18, 5, 9, 0, 12, 29, 31};
        int[] arrayResult = countSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayResult));
    }
}
