package chapter15;

import org.junit.Test;

/**
 * 动态规划 ---- 最优二叉搜索树
 * [注：注意下标的处理] m 的有效范围为 [1~n] ，n 的有效范围为 [0~n-1]
 *
 * @author kissx on 2017/10/25.
 */
public class OptimalBST {

    /**
     * @param p    对应关键字的概率
     * @param q    对应伪关键字的概率
     * @param e    存储子问题结果
     * @param root 存储子问题最优是的根
     * @return 子问题最小期望
     */
    public double oBST(double[] p, double[] q, double[][] e, int[][] root) {
        double[][] w = new double[q.length + 1][q.length];
        for (int i = 1; i < w.length; i++) {
            for (int j = i - 1; j < w[i].length; j++) {
                if (j == i - 1)
                    w[i][j] = q[j];
                else
                    w[i][j] = w[i][j - 1] + p[j] + q[j];
            }
        }
        return oBST_Help(q, 1, q.length - 1, e, root, w);
    }

    /**
     * @param q    同上
     * @param m    start
     * @param n    end
     * @param e    同上
     * @param root 同上
     * @param w    w[i,j]
     * @return 同上
     */
    private double oBST_Help(double[] q, int m, int n, double[][] e, int[][] root, double[][] w) {
        if (m - 1 == n)
            return q[n];
        double min = Double.MAX_VALUE;
        for (int i = m; i <= n; i++) {
            double t = oBST_Help(q, m, i - 1, e, root, w) + oBST_Help(q, i + 1, n, e, root, w) + w[m][n];
            if (t < min) {
                min = t;
                e[m][n] = min;
                root[m][n] = i;
            }
        }
        return min;
    }

//    -------------- TEST --------------

    @Test
    public void test() {
        System.out.println("--------------- BEGIN ---------------");
        double[] p = {-1, 0.15, 0.10, 0.05, 0.10, 0.20};
        double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
        double[][] e = new double[p.length + 1][p.length];
        int[][] root = new int[p.length + 1][p.length];
        double pValue = oBST(p, q, e, root);
        System.out.println("pValue :: " + pValue);
        System.out.println("---------------- END ----------------");
    }

}
