package other;

import org.junit.Test;

/**
 * @author kissx on 2017/11/8.
 */
public class InvestmentProblem {

    private static double investmentProblemHelp(int n, int m, double[][] dataArray, double[][] tempArray, int[][] p) {
        if (n >= dataArray.length)
            return 0;
        if (tempArray[n][m] != 0)
            return tempArray[n][m];
        double max = 0;
        int pos = 0;
        for (int i = 0; i <= m; i++) {
            double c = dataArray[n][i] + investmentProblemHelp(n + 1, m - i, dataArray, tempArray, p);
            if (c > max) {
                max = c;
                pos = i;
            }
        }
        tempArray[n][m] = max;
        p[n][m] = pos;
        return max;
    }

    public static double investmentProblem(double[][] dataArray, double[][] tempArray, int[][] p) {
        return investmentProblemHelp(0, dataArray[0].length - 1, dataArray, tempArray, p);
    }

//    ------------------------ TEST ------------------------

    @Test
    public void test() {
        double[][] dataArray = {{0, 0.11, 0.13, 0.15, 0.21, 0.24, 0.3, 0.35}, {0, 0.12, 0.16, 0.21, 0.23, 0.25, 0.24, 0.34}, {0, 0.08, 0.12, 0.2, 0.24, 0.26, 0.3, 0.35}};
        double[][] tempArray = new double[dataArray.length][dataArray[0].length];
        int[][] p = new int[dataArray.length][dataArray[0].length];
        System.out.println("最大收益为：" + investmentProblem(dataArray, tempArray, p));
        System.out.println("----------- END -----------");
    }

}
