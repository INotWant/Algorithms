package chapter15;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划 ---- 切割钢管问题
 *
 * @author kissx on 2017/10/24.
 */
public class CutRod {

    /**
     * 带备份的自顶向下实现 DP
     *
     * @param pArray 长度为 下标 的钢管的价钱
     * @param n      钢管的总长度
     * @param b      存储 “下标 + 1” 长度钢管的最优价钱
     * @param s      存储 “下标 + 1” 长度钢管的最后一次分割方案
     */
    public static int cutRod1(int[] pArray, int n, int[] b, int[] s) {
        if (n != 0) {
            if (b[n - 1] != -1) {
                return b[n - 1];
            }
            int benefit = 0;
            for (int i = 1; i <= n; i++) {
                int t = cutRod1(pArray, n - i, b, s) + pArray[i - 1];
                if (t > benefit) {
                    benefit = t;
                    b[n - 1] = benefit;
                    s[n - 1] = i;
                }
            }
            return benefit;
        }
        return 0;
    }

    /**
     * 自底向上实现 DP
     */
    public static void cutRod2(int[] pArray, int n, int[] b, int[] s) {
        b[0] = pArray[0];
        int benefit = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int t;
                if (j == i)
                    t = pArray[j - 1];
                else
                    t = b[i - j - 1] + pArray[j - 1];
                if (t > benefit) {
                    benefit = t;
                    b[i - 1] = benefit;
                    s[i - 1] = j;
                }
            }
        }
    }


    /**
     * 打印分割方案
     */
    public static void print(int[] s) {
        int c = s.length - 1;
        StringBuilder sb = new StringBuilder();
        while (c >= 0) {
            sb.append(s[c]).append(",");
            c = c - s[c];
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

//    --------------------- TEST ---------------------

    @Test
    public void testCutRod1() {
        System.out.println("--------- BEGIN ----------");
        int[] pArray = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 9;
        int[] b = new int[n];
        Arrays.fill(b, -1);
        int[] s = new int[n];
        System.out.println("收益 :: " + cutRod1(pArray, n, b, s));
        print(s);
        System.out.println("---------- END ----------");
    }

    @Test
    public void testCutRod2() {
        System.out.println("--------- BEGIN ----------");
        int[] pArray = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 8;
        int[] b = new int[n];
        Arrays.fill(b, -1);
        int[] s = new int[n];
        cutRod2(pArray, n, b, s);
        print(s);
        System.out.println("---------- END ----------");
    }


}
