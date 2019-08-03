package other;

public class _leetcode_3_4 {


    private int help(int[] piles, int sum, int i, int m, int[][] save) {
        if (i >= piles.length)
            return 0;
        if (save[i][m] != 0)
            return save[i][m];
        int max = 0;
        int tSum = 0;
        for (int j = i; j < i + 2 * m && j < piles.length; j++) {
            tSum += piles[j];
            int temp = sum - help(piles, sum - tSum, j + 1, Math.max(m, j - i + 1), save);
            if (temp > max)
                max = temp;
        }
        save[i][m] = max;
        return max;
    }

    public int stoneGameII(int[] piles) {
        int sum = 0;
        for (int pile : piles)
            sum += pile;
        int[][] save = new int[101][101];
        return help(piles, sum, 0, 1, save);
    }

}
