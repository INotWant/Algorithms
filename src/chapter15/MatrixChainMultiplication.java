package chapter15;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划 ---- 矩阵链乘法问题
 *
 * @author kissx on 2017/10/24.
 */
public class MatrixChainMultiplication {

    /**
     * 带备份的自顶向下实现 DP （求 [m-n] 链的最优情况）
     *
     * @param pArray 矩阵链的行列值
     * @param m      矩阵链中第 m 元素
     * @param n      矩阵链中第 n 元素
     * @param b      存储子问题
     * @param s      存储路径
     * @return 相乘次数
     */
    public static int matrixChain1Help(int[] pArray, int m, int n, int[][] b, int[][] s) {
        if (m == n)
            return 0;
        if (b[m - 1][n - 1] != -1)
            return b[m - 1][n - 1];
        int benefit = Integer.MAX_VALUE;
        for (int i = m; i < n; i++) {
            int t = matrixChain1Help(pArray, m, i, b, s) + matrixChain1Help(pArray, i + 1, n, b, s) + pArray[m - 1] * pArray[i] * pArray[n];
            if (t <= benefit) {
                benefit = t;
                b[m - 1][n - 1] = benefit;
                s[m - 1][n - 1] = i;
            }
        }
        return benefit;
    }

    // 解决矩阵链相乘问题的接口
    public static int matrixChain1(int[] pArray, int n, int[][] b, int[][] s) {
        return matrixChain1Help(pArray, 1, n, b, s);
    }

    /**
     * Output
     */
    public void print(int[][] s) {
        StringBuilder sb = new StringBuilder();
        printHelp(s, 0, s.length - 1, sb);
        System.out.println(sb.toString());
    }

    // 巧妙得利用递归，做输出
    private void printHelp(int[][] s, int i, int j, StringBuilder sb) {
        if (i == j)
            sb.append(i + 1);
        else {
            sb.append("(");
            printHelp(s, i, s[i][j] - 1, sb);
            printHelp(s, s[i][j], j, sb);
            sb.append(")");
        }
    }


//    ------------------------------- TEST -------------------------------

    @Test
    public void testMatrixChain1() {
        System.out.println("-------------- BEGIN --------------");
        int[] pArray = {30, 35, 15, 5, 10, 20, 25};
        int n = pArray.length - 1;
        int[][] b = new int[n][n];
        int[][] s = new int[n][n];
        for (int[] aB : b) {
            Arrays.fill(aB, -1);
        }
        System.out.println("最少相乘 :: " + matrixChain1(pArray, n, b, s));
        print(s);
        System.out.println("------------- END --------------");
    }

}
