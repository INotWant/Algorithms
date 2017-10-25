package chapter15;

import org.junit.Test;

/**
 * 动态规划 ---- 最长公共子序列问题
 *
 * @author kissx on 2017/10/25.
 */
public class LCS {

    /**
     * @param mArray 序列 m
     * @param nArray 序列 n
     * @param c      用于存储子问题的最优解 【注：与子问题规模与下标对应】
     * @param s      用于存储子问题合适取得最优
     * @return 最大公共子序列的长度
     */
    public int lcs(char[] mArray, char[] nArray, int[][] c, char[][] s) {
        return lcsHelp(mArray, nArray, mArray.length - 1, nArray.length - 1, c, s);
    }

    private int lcsHelp(char[] mArray, char[] nArray, int m, int n, int[][] c, char[][] s) {
        if (m == -1 || n == -1)
            return 0;
        if (mArray[m] == nArray[n]) {
            int t = lcsHelp(mArray, nArray, m - 1, n - 1, c, s) + 1;
            c[m][n] = t;
            s[m][n] = '＼';
            return t;
        } else {
            int c1 = lcsHelp(mArray, nArray, m - 1, n, c, s);
            int c2 = lcsHelp(mArray, nArray, m, n - 1, c, s);
            int max = c1 >= c2 ? c1 : c2;
            if (max == c1)
                s[m][n] = '←';
            else
                s[m][n] = '↑';
            c[m][n] = max;
            return max;
        }
    }

    /**
     * 用于构造最优解
     */
    public void print(char[][] s, char[] mArray) {
        int m = s.length - 1;
        int n = s[0].length - 1;
        StringBuilder sb = new StringBuilder();
        while (m >= 0 && n >= 0) {
            if (s[m][n] == '＼') {
                sb.insert(0, mArray[m] + ",");
                m--;
                n--;
            } else if (s[m][n] == '←')
                m--;
            else
                n--;
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

//    --------------------- TEST ---------------------

    @Test
    public void testLCS() {
        System.out.println("--------------- BEGIN ---------------");
//        char[] mArray = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] mArray = {'B','C','A'};
//        char[] nArray = {'B', 'D', 'C', 'A', 'B', 'A'};
        char[] nArray = {'B','A'};
        int[][] c = new int[mArray.length][nArray.length];
        char[][] s = new char[mArray.length][nArray.length];
        lcs(mArray, nArray, c, s);
        System.out.printf("Output :: ");
        print(s, mArray);
        System.out.println("---------------- END ----------------");
    }

}
