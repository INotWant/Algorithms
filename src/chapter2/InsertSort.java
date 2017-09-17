package chapter2;

import java.util.Arrays;

/**
 * @author kissx on 2017/9/17.
 * 插入排序
 */
public class InsertSort {

     private static void insertSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i-1;
            for (; j >= 0 ; j--) {
                if (array[j] > temp){
                    array[j + 1] = array[j];
                }else
                    break;
            }
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {2,1,18,6,4,1,9,7};
        System.out.println("【原数组】：" + Arrays.toString(array));
        insertSort(array);
        System.out.println("【排序后】：" + Arrays.toString(array));
    }
}
