package other;

/**
 * @author iwant
 * @date 2019-04-26 14:55
 */
public class P200 {

    private void helper(char[][] grid, boolean[] flags, int i, int j) {
        int len1 = grid.length;
        int len2 = grid[0].length;
        if (grid[i][j] == '1' && !flags[i * len2 + j]) {
            flags[i * len2 + j] = true;
            if (i - 1 >= 0)
                helper(grid, flags, i - 1, j);
            if (i + 1 < len1)
                helper(grid, flags, i + 1, j);
            if (j - 1 >= 0)
                helper(grid, flags, i, j - 1);
            if (j + 1 < len2)
                helper(grid, flags, i, j + 1);
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        boolean[] flags = new boolean[grid.length * grid[0].length];
        int result = 0;
        int len1 = grid.length;
        int len2 = grid[0].length;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == '1' && !flags[i * len2 + j]) {
                    ++result;
                    helper(grid, flags, i, j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1'},
                         {'0', '1', '0'},
                         {'1', '1', '1'}};
        P200 p200 = new P200();
        System.out.println(p200.numIslands(grid));
    }

}
