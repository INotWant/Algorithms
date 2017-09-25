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
        for (int i = 1; i < kArray.length; i++) {
            kArray[i] += kArray[i - 1];
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
        int[] arrayResult = Arrays.copyOf(array, array.length);
        for (int i = b - 1; i >= 0; i--) {
            arrayResult = bitSort(bitArray(arrayResult,formatStr,b,i), arrayResult);
        }
        return arrayResult;
    }

    private static int[] bitArray(int[] originalArray,StringBuilder formatStr,int b,int k){
        StringBuilder tempSB = new StringBuilder(formatStr);
        int[] bitArray = new int[originalArray.length];
        for (int i = 0; i < bitArray.length; i++) {
            String aStr = String.valueOf(originalArray[i]);
            aStr = tempSB.toString().substring(0, b - aStr.length()) + aStr;
            bitArray[i] = aStr.charAt(k) - '0';
        }
        return bitArray;
    }


    public static void main(String[] args) {
        long sTime = System.currentTimeMillis();
        int[] array = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
//            array[i] = i + 1;
            array[i] = 100000000 - i;
        }
        baseSort1(array);
        long eTime = System.currentTimeMillis();
        System.out.println("总时间：" + (eTime - sTime));
    }
}
