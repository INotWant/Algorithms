package chapter8;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author kissx on 2017/9/25.
 *         <p>
 *         桶排序
 */
public class BarrelSort {

    /**
     *
     * @param array 输入排序序列（要求元素范围在 [0,1) 且为 double 类型）
     * @return 排序结果
     */
    public static double[] barrelSort(double[] array) {
        double[] arrayResult = new double[array.length];
        int n = array.length;
        double[][] barrelArray = new double[n][n];
        for (double anArray : array) {
            int xPos = (int) Math.floor(anArray * n);
            int yPos = (int) ++barrelArray[xPos][0];
            barrelArray[xPos][yPos] = anArray;
        }
        for (double[] aBarrelArray : barrelArray) sort(aBarrelArray);
        int pos = 0;
        for (double[] aBarrelArray : barrelArray) {
            for (int j = 1; j <= aBarrelArray[0]; j++) {
                arrayResult[pos++] = aBarrelArray[j];
            }
        }
        return arrayResult;
    }

    private static void sort(double[] array) {
        if (array.length > 2) {
            for (int i = 2; i < array[0]; i++) {
                double currentElement = array[i];
                int j = i - 1;
                for (; j >= 1; j--) {
                    if (array[j] > currentElement)
                        array[j + 1] = array[j];
                    else
                        break;
                }
                array[j + 1] = currentElement;
            }
        }
    }

    @Test
    public void test1() {
        double[] array = {0.8, 0.2, 0.1, 0.9, 0, 0.1, 0.5};
        double[] arrayResult = barrelSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayResult));
    }
}
