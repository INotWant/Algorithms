package chapter25;

import org.junit.Test;

/**
 * 计算传递闭包 (极类似 FloydWarshall 算法)
 *
 * @author kissx on 2017/12/10.
 */
public class TransitiveClosure {

    /**
     * @param w 权重矩阵
     * @return 若果 i -> j 可达则为 true ，否则为 false （i，j分别表示 行、列 标号）
     */
    public static boolean[][] transitiveClosure(double[][] w) {
        boolean[][] b = new boolean[w.length][w.length];
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w.length; j++) {
                b[i][j] = w[i][j] != Double.MAX_VALUE;
            }
        }
        for (int k = 0; k < w.length; k++)
            for (int i = 0; i < w.length; i++) {
                boolean[] rowB = new boolean[w.length];
                for (int j = 0; j < w.length; j++)
                    rowB[j] = b[i][j] || (b[i][k] && b[k][j]);
                b[i] = rowB;
            }
        return b;
    }

    // -------------TEST-------------

    @Test
    public void test() {
        double[][] w =
                {
                        {0, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE},
                        {Double.MAX_VALUE, 0, 1, 1},
                        {Double.MAX_VALUE, 1, 0, Double.MAX_VALUE},
                        {1, Double.MAX_VALUE, 1, 0}
                };
        boolean[][] booleans = transitiveClosure(w);
        for (int i = 0; i < booleans.length; i++) {
            for (int j = 0; j < booleans.length; j++) {
                if (booleans[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

}
