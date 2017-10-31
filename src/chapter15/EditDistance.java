package chapter15;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划 ---- 编辑距离
 *
 * @author kissx on 2017/10/31.
 */
public class EditDistance {

    public static int COPY_COST;    // 复制代价
    public static int REPLACE_COST; // 替换代价
    public static int DELETE_COST;  // 删除代价
    public static int INSERT_COST;  // 插入代价
    public static int TWIDDLE_COST; // 旋转代价
    public static int KILL_COST;    // 终止代价

    /**
     * 思想：使用 e[i][j] 记录 x [i,m] → y [j,n] 的最小编辑距离。
     *
     * @param x 要修改的串
     * @param y 目标串
     * @param e 中间变量，存储子问题最优
     * @param s 中间变量，存储子问题选择
     * @return 代价
     */
    public int editDistance(char[] x, char[] y, int[][] e, int[][] s) {
        return editDistanceHelp(x, y, 0, 0, e, s);
    }

    // 递归辅助方法
    private int editDistanceHelp(char[] x, char[] y, int i, int j, int[][] e, int[][] s) {
        if (i <= x.length && j <= y.length) {
            if (i <= x.length && j <= y.length && e[i][j] != -1)
                return e[i][j];
            int[] distance = new int[6];
            Arrays.fill(distance, Integer.MAX_VALUE);
            // 1. copy
            if (i < x.length && j < y.length && x[i] == y[j])
                distance[0] = COPY_COST + editDistanceHelp(x, y, i + 1, j + 1, e, s);
            // 2. replace
            if (i < x.length)
                distance[1] = REPLACE_COST + editDistanceHelp(x, y, i + 1, j + 1, e, s);
            // 3. delete
            if (i < x.length)
                distance[2] = DELETE_COST + editDistanceHelp(x, y, i + 1, j, e, s);
            // 4. insert
            if (j < y.length)
                distance[3] = INSERT_COST + editDistanceHelp(x, y, i, j + 1, e, s);
            // 5. twiddle
            if (i + 1 < x.length && j + 1 < y.length && x[i] == y[j + 1] && y[j] == x[i + 1])
                distance[4] = TWIDDLE_COST + editDistanceHelp(x, y, i + 2, j + 2, e, s);
            // 6. kill
            if (j == y.length)
                distance[5] = KILL_COST;
            int min = distance[0];
            int num = 1;
            for (int k = 1; k < 6; k++) {
                if (min > distance[k]) {
                    min = distance[k];
                    num = k + 1;
                }
            }
            e[i][j] = min;
            s[i][j] = num;
            return min;
        }
        return 0;
    }

    /**
     * 最优编辑过程
     *
     * @param x 要修改的串
     * @param y 目标串
     * @param s 中间变量，存储子问题选择
     */
    public void print(char[] x, char[] y, int[][] s) {
        System.out.println(Arrays.toString(x) + " → " + Arrays.toString(y) + " :");
        int i = 0;
        int j = 0;
        while (s[i][j] != 6) {
            switch (s[i][j]) {
                case 1:
                    System.out.println("\tCopy :: " + x[i]);
                    ++i;
                    ++j;
                    break;
                case 2:
                    System.out.println("\tReplace :: " + x[i] + "→" + y[j]);
                    ++i;
                    ++j;
                    break;
                case 3:
                    System.out.println("\tDelete :: " + x[i]);
                    ++i;
                    break;
                case 4:
                    System.out.println("\tInsert :: " + y[j]);
                    ++j;
                    break;
                case 5:
                    System.out.println("\tTwiddle :: " + x[i] + x[i + 1] + "→" + x[i + 1] + x[i]);
                    i += 2;
                    j += 2;
                    break;
            }
        }
        System.out.println("\tKill");
    }

    // ------------------ TEST ------------------

    @Test
    public void test() {
        System.out.println("--------------- BEGIN ---------------");
        COPY_COST = 1;
        REPLACE_COST = 3;
        DELETE_COST = 1;
        INSERT_COST = 3;
        TWIDDLE_COST = 2;
        KILL_COST = 1;

//        char[] x = {'c', 'a', 't'};
//        char[] y = {'r', 'c', 'a', 't'};

        char[] x = "algorithm".toCharArray();
        char[] y = "altruistic".toCharArray();

//        char[] x = "".toCharArray();
//        char[] y = "abc".toCharArray();

        int[][] e = new int[x.length + 1][y.length + 1];
        int[][] s = new int[x.length + 1][y.length + 1];
        for (int[] anE : e) Arrays.fill(anE, -1);
        int distance = editDistance(x, y, e, s);
        System.out.println(Arrays.toString(x) + " → " + Arrays.toString(y) + " :: " + distance);
        print(x, y, s);
        System.out.println("---------------- END ----------------");
    }

}
