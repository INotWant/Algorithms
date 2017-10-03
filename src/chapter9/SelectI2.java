package chapter9;

import chapter7.QuickSort;

import java.util.Arrays;

/**
 * @author kissx on 2017/10/3.
 *         <p>
 *         最坏为 O（n） 的选择选择算法
 */
public class SelectI2 {

    public static int select(int[] array, int i) {
        int[] tempArray = Arrays.copyOfRange(array, 0, array.length);
        return select(tempArray, 0, array.length - 1, i);
    }

    /**
     * @param array 待处理数组
     * @param start 此次处理的开始位置
     * @param end   此次处理的结束位置
     * @param i     查询第 i 小的元素
     * @return 返回第 i 小的元素
     */
    private static int select(int[] array, int start, int end, int i) {
        array = Arrays.copyOfRange(array, start, end + 1);
        // 1) 分组，每组有 5 个元素
        // 2) 选出每个组（共 5 个元素）中的中位数【注：偶数个时，下标较小者】
        int[] medianArrayPos = getMediansPos(array);
        // 3) 选出第 2 步所得所有中位数的中位数（注意是要得到其所在待处理数组的位置）
        int[] medianArray = new int[medianArrayPos.length];
        for (int j = 0; j < medianArray.length; j++) {
            medianArray[j] = array[medianArrayPos[j]];
        }
        int medainArrayMedainPos = getMedianPos(medianArray, medianArrayPos);
        // 4) 根据选出的中位数数组的中位数划分待处理数组（基于快排的划分方法）
        int tempEnd = array[end - start];
        array[end - start] = array[medainArrayMedainPos];
        array[medainArrayMedainPos] = tempEnd;
        int pPos = partition(array, 0, end - start);
        // 5) 根据划分情况
        if (pPos + 1 == i)
            return array[pPos];
        else if (pPos + 1 > i)
            return select(array, 0, pPos - 1, i);
        else
            return select(array, pPos + 1, end - start, i - pPos - 1);
    }

    /**
     * @param array 待处理的数组
     * @return 各组中位数所处位置的集合
     */
    private static int[] getMediansPos(int[] array) {
        int size = array.length % 5 == 0 ? array.length / 5 : array.length / 5 + 1;
        int[] medianArray = new int[size];
        for (int i = 0; i < size; i++) {
            int end = (i + 1) * 5 - 1;
            if (i == size - 1)
                end = array.length - 1;
            medianArray[i] = getMedianPos(array, i * 5, end);
        }
        return medianArray;
    }

    /**
     * 获取某组中的中位数所处的位置（一般位置是固定的，前提是此组已经有序）
     */
    private static int getMedianPos(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int temp = array[i];
            for (int j = i - 1; j >= start; j--) {
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                    array[j] = temp;
                } else
                    break;
            }
        }
        int length = end - start + 1;
        int mPos = length % 2 == 0 ? length / 2 - 1 : length / 2;
        return mPos + start;
    }

    /**
     * 获取中位数数组的中位数所在待处理数组的位置
     */
    private static int getMedianPos(int[] medianArray, int[] medianArrayPos) {
        for (int i = 1; i < medianArray.length; i++) {
            int temp = medianArray[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp < medianArray[j]) {
                    medianArray[j + 1] = medianArray[j];
                    medianArray[j] = temp;
                    int posTemp = medianArrayPos[j + 1];
                    medianArrayPos[j + 1] = medianArrayPos[j];
                    medianArrayPos[j] = posTemp;
                } else
                    break;
            }
        }
        int mPos = medianArray.length % 2 == 0 ? medianArray.length / 2 - 1 : medianArray.length / 2;
        return medianArrayPos[mPos];
    }

    /**
     * 快排的划分方法
     */
    private static int partition(int[] array, int start, int end) {
        int i = start - 1;
        int base = array[end];
        for (int j = start; j < end; j++) {
            if (base >= array[j]) {
                ++i;
                if (j != i) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        array[end] = array[i + 1];
        array[i + 1] = base;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int result = select(array, 11);
        System.out.println("第 11 小的元素为：" + result);
        QuickSort.quickSortFunction(array);
        System.out.println("[原数组] " + Arrays.toString(array));
    }
}
