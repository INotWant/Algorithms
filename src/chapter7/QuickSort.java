package chapter7;

/**
 * @author kissx on 2017/9/23.
 *         快速排序
 */
public class QuickSort {

    public static void quickSortFunction(int[] array) {
        quickSortFunction(array, 0, array.length - 1);
    }

    public static void quickSortFunction(int[] array, int start, int end) {
        if (end > start) {
            int pos = partition(array, start, end);
            quickSortFunction(array, start, pos - 1);
            quickSortFunction(array, pos + 1, end);
        }
    }

    public static int partition(int[] array, int start, int end) {
        int i = start - 1;      // 注意 i 的意义：i 指向的是当前小于或等于 base 的最大位置处
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
        long sTime = System.currentTimeMillis();
        int[] array = new int[100000];
        for (int i = 0; i < 100000; i++) {
            array[i] = 100000 - i;
        }
        quickSortFunction(array);
        long eTime = System.currentTimeMillis();
        System.out.println("总时间：" + (eTime - sTime));
    }

}
