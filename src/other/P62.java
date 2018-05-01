package other;

import java.util.Arrays;

public class P62 {

    /*
    public int uniquePaths(int m, int n) {
        int count = m + n - 2;
        int opt = Math.min(m, n) - 1;
        long num = 1;
        for (int i = 0; i < opt; i++)
            num *= (count - i);
        for (int i = 1; i <= opt; i++)
            num /= i;
        return (int) num;
    }
    */

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(array[i], -1);
        return helper(m - 1, n - 1, array);
    }

    private static int helper(int i, int j, int[][] array) {
        if (array[i][j] != -1)
            return array[i][j];
        if (i == 0 && j == 0)
            return 1;
        else {
            if (i == 0) {
                array[i][j] = helper(i, j - 1, array);
                return array[i][j];
            } else if (j == 0) {
                array[i][j] = helper(i - 1, j, array);
                return array[i][j];
            } else {
                array[i][j] = helper(i - 1, j, array) + helper(i, j - 1, array);
                return array[i][j];
            }
        }
    }

    public static void main(String[] args) {
        P62 p62 = new P62();
        System.out.println(p62.uniquePaths(19, 13));
    }

}
