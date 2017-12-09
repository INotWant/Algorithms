package chapter25;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 所有结点对的最短路径问题
 *
 * @author kissx on 2017/12/9.
 */
public class AllPairsShortestPaths {

    /**
     * 类矩阵乘法求法（自底向上）
     *
     * @param w 权重矩阵（使用邻接矩阵表示）
     * @return 结点对之间的最短路径权重
     */
    public static double[][] matrixMultipEnd2Top(double[][] w) {
        double[][] p = new double[w.length][w.length];
        for (int i = 0; i < w.length; i++) {
            p[i] = Arrays.copyOf(w[i], w[i].length);
        }
        for (int k = 2; k < w.length; ++k) {
            for (int i = 0; i < w.length; i++) {
                double[] rowP = new double[w.length];
                for (int j = 0; j < w.length; j++) {
                    double min = Double.MAX_VALUE;
                    for (int l = 0; l < w.length; l++) {
                        if (p[i][l] != Double.MAX_VALUE && w[l][j] != Double.MAX_VALUE) {
                            if (min > p[i][l] + w[l][j])
                                min = p[i][l] + w[l][j];
                        }
                    }
                    if (min != Double.MAX_VALUE)
                        rowP[j] = min;
                    else
                        rowP[j] = p[i][j];
                }
                p[i] = rowP;
            }
        }
        return p;
    }

    /**
     * @param p 所有结点对最短路径描述
     * @param w 权重矩阵
     * @return “前驱子图“
     */
    public static int[][] getPath(double[][] p, double[][] w) {
        int[][] pArray = new int[p.length][p.length];
        for (int i = 0; i < p.length; i++)
            Arrays.fill(pArray[i], -1);
        for (int i = 0; i < p.length; i++) {
            LinkedList<Integer> list = new LinkedList<>();  // 使用队列
            for (int j = 0; j < p.length; j++) {
                if (i == j)
                    pArray[i][j] = 0;
                else if (p[i][j] == w[i][j]) {
                    pArray[i][j] = i + 1;
                    list.addLast(j);
                }
            }
            while (list.size() > 0) {   // 查找
                int c = list.removeFirst();
                for (int j = 0; j < p.length; j++) {
                    if (c != j && w[c][j] != Double.MAX_VALUE)
                        if (pArray[i][j] == -1 && p[i][j] == p[i][c] + w[c][j]) {
                            pArray[i][j] = c + 1;
                            list.addLast(j);
                        }
                }
            }
        }
        return pArray;
    }

//    ----------------------TEST----------------------

    @Test
    public void testMatrixMultipEnd2Top() {
        // 测试类矩阵乘法的所有结点对的最短路径
        double[][] w =
                {
                        {0, 3, 8, Double.MAX_VALUE, -4},
                        {Double.MAX_VALUE, 0, Double.MAX_VALUE, 1, 7},
                        {Double.MAX_VALUE, 4, 0, Double.MAX_VALUE, Double.MAX_VALUE},
                        {2, Double.MAX_VALUE, -5, 0, Double.MAX_VALUE},
                        {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 6, 0},
                };
        double[][] doubles = matrixMultipEnd2Top(w);
        for (double[] aDouble : doubles) {
            for (int j = 0; j < doubles.length; j++) {
                if (aDouble[j] == Double.MAX_VALUE)
                    System.out.print("∞, ");
                else
                    System.out.print(aDouble[j] + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void testGetPath() {
        double[][] w =
                {
                        {0, 3, 8, Double.MAX_VALUE, -4},
                        {Double.MAX_VALUE, 0, Double.MAX_VALUE, 1, 7},
                        {Double.MAX_VALUE, 4, 0, Double.MAX_VALUE, Double.MAX_VALUE},
                        {2, Double.MAX_VALUE, -5, 0, Double.MAX_VALUE},
                        {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 6, 0},
                };
        int[][] pathArray = getPath(matrixMultipEnd2Top(w), w);
        for (int[] aPathArray : pathArray) {
            for (int j = 0; j < pathArray.length; j++)
                System.out.print(aPathArray[j] + ", ");
            System.out.println();
        }
    }

}
