package other;

public class _leetcode_3_3 {


    public int largest1BorderedSquare(int[][] grid) {
        if (grid == null)
            return 0;
        int currMax = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = currMax; k <= grid.length; k++) {
                    if (isZFX(grid, i, j, k))
                        currMax = k;
                }
            }
        }
        return currMax * currMax;
    }

    private boolean isZFX(int[][] grid, int xs, int ys, int len) {
        if (len == 1) {
            return grid[xs][ys] == 1;
        } else {
            if (xs + len > grid.length || ys + len > grid[0].length)
                return false;
            for (int i = xs; i < xs + len; i++) {
                if (grid[i][ys] == 0 || grid[i][ys + len - 1] == 0)
                    return false;
            }
            for (int i = ys; i < ys + len; i++) {
                if (grid[xs][i] == 0 || grid[xs + len - 1][i] == 0)
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        _leetcode_3_3 obj = new _leetcode_3_3();
        System.out.println(obj.largest1BorderedSquare(grid));
    }

}
