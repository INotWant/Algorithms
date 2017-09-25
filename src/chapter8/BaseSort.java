package chapter8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * （不完美）
     * @param array 大于等于零的正整数
     * @return 排序结果
     */
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
            arrayResult = bitSort(bitArray1(arrayResult,formatStr,b,i), arrayResult);
        }
        return arrayResult;
    }

    private static int[] bitArray1(int[] originalArray,StringBuilder formatStr,int b,int k){
        StringBuilder tempSB = new StringBuilder(formatStr);
        int[] bitArray = new int[originalArray.length];
        for (int i = 0; i < bitArray.length; i++) {
            String aStr = String.valueOf(originalArray[i]);
            aStr = tempSB.toString().substring(0, b - aStr.length()) + aStr;
            bitArray[i] = aStr.charAt(k) - '0';
        }
        return bitArray;
    }

    /**
     * （不完美，突然想到转换为数字可以使用【计数排序】）
     * @param dateList 输入的日期序列，每个日期格式为：YYYY,MM,DD
     * @return 排好序的日期序列，每个日期格式为：YYYYMMDD
     */
    public static int[] baseSort2(List<String> dateList){
        int[] arrayResult = new int[dateList.size()];
        for (int i = 0; i < arrayResult.length; i++) {
            String[] dateStr = dateList.get(i).split(",");
            arrayResult[i] = Integer.parseInt(dateStr[0] + dateStr[1] + dateStr[2]);
        }
        for (int i = 2; i >= 0; i--) {
            arrayResult = bitSort(bitArray2(arrayResult,i),arrayResult);
        }
        return arrayResult;
    }

    private static int[] bitArray2(int[] originalArray,int k){
        int[] bitArray = new int[originalArray.length];
        for (int i = 0; i < bitArray.length; i++) {
            // 注意这里写死了，不好！
            if (k == 0){
                bitArray[i] = Integer.parseInt(String.valueOf(originalArray[i]).substring(0,4));
            }else if (k == 1){
                bitArray[i] = Integer.parseInt(String.valueOf(originalArray[i]).substring(4,6));
            }else {
                bitArray[i] = Integer.parseInt(String.valueOf(originalArray[i]).substring(6,8));
            }
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

    /**
     * 测试使用 基数排序 对日期排序
     */
    @Test
    public void test(){
        List<String> dateList = new ArrayList<>();
        dateList.add("1997,01,02");
        dateList.add("1996,02,12");
        dateList.add("1997,02,03");
        dateList.add("1996,01,01");
        dateList.add("2000,12,21");
        dateList.add("2010,02,03");
        int[] arrayList = baseSort2(dateList);
        System.out.println(Arrays.toString(arrayList));
    }
}
