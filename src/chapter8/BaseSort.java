package chapter8;

import java.util.Arrays;

/**
 * @author kissx on 2017/9/24.
 *         <p>
 *         基数排序
 */
public class BaseSort {

    public static int[] bitSort(int[] bitArray, int[] originalArray) {
        int[] arrayResult = new int[originalArray.length];
        int min = bitArray[0];
        int max = bitArray[0];
        for (int i = 1; i < bitArray.length; i++) {
            if (bitArray[i] < min)
                min = bitArray[i];
            else if (bitArray[i] > max)
                max = bitArray[i];
        }
        int[] kArray = new int[max - min + 1];
        for (int a : bitArray) {
            kArray[a - min]++;
        }
        for (int i = 1; i < bitArray.length; i++) {
            bitArray[i] += bitArray[i - 1];
        }
        for (int i = bitArray.length - 1; i >= 0; i--) {
            arrayResult[--kArray[bitArray[i] - min]] = originalArray[i];
        }
        return arrayResult;
    }

    public static int[] baseSort1(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int b = String.valueOf(max).length();
        StringBuilder formatStr = new StringBuilder();
        for (int i = 0; i < b; i++) {
            formatStr.append("0");
        }
        int[][] arrayTmp = new int[b][array.length];
        for (int i = 0; i < array.length; i++) {
            String aStr = String.valueOf(array[i]);
            aStr = formatStr.toString().substring(0, b - aStr.length()) + aStr;
            for (int j = 0; j < aStr.length(); j++) {
                arrayTmp[j][i] = aStr.charAt(j) - '0';
            }
        }
        int[] arrayResult = Arrays.copyOf(array, array.length);
        for (int i = b - 1; i >= 0; i--) {
            arrayResult = bitSort(arrayTmp[i], arrayResult);
        }
        return arrayResult;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0, 2, 1, 20, 18, 5, 9, 0, 12, 29, 31};
        int[] arrayResult = baseSort1(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayResult));
    }
}
