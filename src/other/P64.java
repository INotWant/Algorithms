package other;

import java.util.Arrays;

public class P64 {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int x = grid.length;
        int y = grid[0].length;
        int[][] saveArray = new int[x][y];
        for (int[] array : saveArray)
            Arrays.fill(array, -1);
        return helper(grid, x - 1, y - 1, saveArray);
    }

    public static int helper(int[][] grid, int x, int y, int[][] saveArray) {
        if (saveArray[x][y] != -1)
            return saveArray[x][y];
        int temp;
        if (x == 0 && y == 0) {
            temp = grid[x][y];

        } else if (x == 0) {
            temp = helper(grid, x, y - 1, saveArray) + grid[x][y];

        } else if (y == 0) {
            temp = helper(grid, x - 1, y, saveArray) + grid[x][y];
        } else {
            temp = Math.min(helper(grid, x - 1, y, saveArray), helper(grid, x, y - 1, saveArray)) + grid[x][y];
        }
        saveArray[x][y] = temp;
        return temp;
    }

}
