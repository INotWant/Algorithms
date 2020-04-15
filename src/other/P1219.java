package other;

public class P1219 {

    private int result;

    public int getMaximumGold(int[][] grid) {
        int xLen = grid.length;
        int yLen = grid[0].length;
        result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                helper(new boolean[xLen][yLen], grid, i, j, 0);
            }
        }
        return result;
    }

    private void helper(boolean[][] flags, int[][] grid, int x, int y, int currSum) {
        int xLen = flags.length;
        int yLen = flags[0].length;
        if (x < 0 || y < 0 || x >= xLen || y >= yLen || grid[x][y] == 0 || flags[x][y]) {
            result = Math.max(currSum, result);
            return;
        }
        flags[x][y] = true;
        currSum += grid[x][y];
        helper(flags, grid, x - 1, y, currSum);
        helper(flags, grid, x + 1, y, currSum);
        helper(flags, grid, x, y - 1, currSum);
        helper(flags, grid, x, y + 1, currSum);
        flags[x][y] = false;
    }

}
