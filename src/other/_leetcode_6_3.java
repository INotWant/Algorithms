package other;

public class _leetcode_6_3 {

    public int maxDistance(int[][] grid) {
        int rLen = grid.length;
        int cLen = grid[0].length;
        boolean[][] accessed = new boolean[rLen][cLen];
        int[] next = new int[rLen * cLen];
        int[] tNext = new int[rLen * cLen];
        int nextNum = 0;

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1) {
                    next[nextNum++] = i * cLen + j;
                    accessed[i][j] = true;
                }
            }
        }
        if (nextNum == 0 || nextNum == rLen * cLen)
            return -1;

        int totalNum = nextNum;
        int result = 0;
        while (totalNum < rLen * cLen) {
            ++result;
            int nNextNum = 0;
            for (int i = 0; i < nextNum; i++) {
                int n = next[i];
                int r = n / cLen;
                int c = n % cLen;
                if (r - 1 >= 0 && !accessed[r - 1][c]) {
                    tNext[nNextNum++] = (r - 1) * cLen + c;
                    accessed[r - 1][c] = true;
                }
                if (r + 1 < rLen && !accessed[r + 1][c]) {
                    tNext[nNextNum++] = (r + 1) * cLen + c;
                    accessed[r + 1][c] = true;
                }
                if (c - 1 >= 0 && !accessed[r][c - 1]) {
                    tNext[nNextNum++] = r * cLen + c - 1;
                    accessed[r][c - 1] = true;
                }
                if (c + 1 < cLen && !accessed[r][c + 1]) {
                    tNext[nNextNum++] = r * cLen + c + 1;
                    accessed[r][c + 1] = true;
                }
            }
            int[] tArr = next;
            next = tNext;
            tNext = tArr;
            nextNum = nNextNum;
            totalNum += nextNum;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        _leetcode_6_3 obj = new _leetcode_6_3();
        System.out.println(obj.maxDistance(grid));
    }

}
