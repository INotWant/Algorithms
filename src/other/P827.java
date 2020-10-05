package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P827 {

    public int largestIsland(int[][] grid) {
        int[][] arr1 = new int[grid.length][grid[0].length];

        int count = 1;
        int xLen = grid.length;
        int yLen = grid[0].length;
        List<Integer> saveList = new ArrayList<>();
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                if (grid[i][j] == 1) {
                    help(grid, i, j, saveList);
                    for (int num : saveList) {
                        int x = num / yLen;
                        int y = num % yLen;
                        grid[x][y] = saveList.size();
                        arr1[x][y] = count;
                    }
                    saveList.clear();
                    count++;
                }
            }
        }

        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                int tmp = 1;
                if (grid[i][j] == 0) {
                    if (i - 1 >= 0 && grid[i - 1][j] != 0) {
                        tmp += grid[i - 1][j];
                        set.add(arr1[i - 1][j]);
                    }
                    if (i + 1 < xLen && grid[i + 1][j] != 0) {
                        if (!set.contains(arr1[i + 1][j])) {
                            tmp += grid[i + 1][j];
                            set.add(arr1[i + 1][j]);
                        }
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] != 0) {
                        if (!set.contains(arr1[i][j - 1])) {
                            tmp += grid[i][j - 1];
                            set.add(arr1[i][j - 1]);
                        }
                    }
                    if (j + 1 < yLen && grid[i][j + 1] != 0) {
                        if (!set.contains(arr1[i][j + 1])) {
                            tmp += grid[i][j + 1];
                            set.add(arr1[i][j + 1]);
                        }
                    }
                    if (tmp > result) {
                        result = tmp;
                    }
                    set.clear();
                }
            }
        }

        if (result == 0) {
            result = xLen * yLen;
        }

        return result;
    }

    private void help(int[][] grid, int x, int y, List<Integer> saveList) {
        int xLen = grid.length;
        int yLen = grid[0].length;
        if (x < 0 || y < 0 || x >= xLen || y >= yLen || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 2;
        saveList.add(x * yLen + y);
        help(grid, x - 1, y, saveList);
        help(grid, x + 1, y, saveList);
        help(grid, x, y - 1, saveList);
        help(grid, x, y + 1, saveList);
    }

    public static void main(String[] args) {
        P827 p827 = new P827();
        int[][] grid = {{1, 1}, {1, 0}};
        System.out.println(p827.largestIsland(grid));
    }

}
