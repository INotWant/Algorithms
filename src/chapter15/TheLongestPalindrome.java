package chapter15;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划 ---- 最长回文子序列
 * 思想：可以把此问题转换为 "原text" 与 "逆序text" 的最长公共子序列问题
 *
 * @author kissx on 2017/10/29.
 */
public class TheLongestPalindrome {

    // 极类似 LCS
    private int longestPalindromeHelp(char[] x, char[] y, int m, int n, int[][] num, char[][] s) {
        if (m >= 0 && n >= 0) {
            if (num[m][n] != -1)
                return num[m][n];
            if (x[m] == y[n]) {
                int max = longestPalindromeHelp(x, y, m - 1, n - 1, num, s) + 1;
                num[m][n] = max;
                s[m][n] = '↖';
                return max;
            } else {
                int max1 = longestPalindromeHelp(x, y, m - 1, n, num, s);
                int max2 = longestPalindromeHelp(x, y, m, n - 1, num, s);
                int max = max1 < max2 ? max2 : max1;
                num[m][n] = max;
                if (max == max1)
                    s[m][n] = '↑';
                else
                    s[m][n] = '←';
                return max;
            }
        }
        return 0;
    }

    /**
     * 把此问题转换为 "原text" 与 "逆序text" 的最长公共子序列问题
     *
     * @param text 原字符串
     * @param num  用于存储子问题的解
     * @param s    用于存储子问题集最优路径
     * @return 问题的最优解
     */
    public int longestPalindrome(String text, int[][] num, char[][] s) {
        return longestPalindromeHelp(text.toCharArray(), new StringBuilder(text).reverse().toString().toCharArray(), text.length() - 1, text.length() - 1, num, s);
    }

    /**
     * 用于构造最优解
     */
    public void print(char[][] s, char[] mArray) {
        int m = s.length - 1;
        int n = m;
        StringBuilder sb = new StringBuilder();
        while (m >= 0 && n >= 0) {
            if (s[m][n] == '↖') {
                sb.insert(0, mArray[m] + ",");
                m--;
                n--;
            } else if (s[m][n] == '↑')
                m--;
            else
                n--;
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    // ----------------------- TEST -----------------------

    @Test
    public void test() {
        String text = "character";
        int[][] num = new int[text.length()][text.length()];
        for (int[] aNum : num) Arrays.fill(aNum, -1);
        char[][] s = new char[text.length()][text.length()];
        int length = longestPalindrome(text, num, s);
        System.out.println("Length :: " + length);
        System.out.println(text + " :: ");
        print(s, text.toCharArray());
    }

}
