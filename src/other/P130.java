package other;

import java.util.HashSet;
import java.util.Set;

public class P130 {

    // TODO 重写
    /*
    private boolean dfs(char[][] board, int i, int j, int[] saveArray) {
        if (2 == saveArray[i * board[0].length + j] || i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
            saveArray[i * board[0].length + j] = 2;
            return false;
        } else {
            saveArray[i * board[0].length + j] = 1;
            boolean tResult = 2 != saveArray[(i - 1) * board[0].length + j];
            if (tResult) {
                tResult = 2 != saveArray[i * board[0].length + j - 1];
            }
            if ('O' == board[i][j + 1] && tResult) {
                tResult = dfs(board, i, j + 1, saveArray);
            }
            if ('O' == board[i + 1][j] && tResult) {
                tResult = dfs(board, i + 1, j, saveArray);
            }
            if ('O' == board[i][j - 1] && tResult) {
                if (saveArray[i * board[0].length + j - 1] != 1) {
                    tResult = dfs(board, i, j - 1, saveArray);
                }
            }
            if (tResult) {
                if ('X' == board[i][j - 1]) {
                    board[i][j] = 'X';
                }
            } else {
                saveArray[i * board[0].length + j] = 2;
            }
            return tResult;
        }
    }

    public void solve(char[][] board) {
        if (null == board || 0 == board.length) {
            return;
        }
        int xSize = board.length;
        int ySize = board[0].length;
        int[] saveArray = new int[xSize * ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if ('O' == board[i][j] && 2 != saveArray[i * ySize + j]) {
                    dfs(board, i, j, saveArray);
                }
            }
        }
    }
    */

    private void dfs(char[][] board, int i, int j, boolean[][] saveArray, boolean[] flagArray, Set<Integer> set) {
        int xSize = board.length;
        int ySize = board[0].length;
        saveArray[i][j] = true;
        set.add(i * ySize + j);
        if (!flagArray[0] && (i == 0 || i == xSize - 1 || j == 0 || j == ySize - 1)) {
            flagArray[0] = true;
        }
        if (i != 0 && board[i - 1][j] == 'O' && !saveArray[i - 1][j]) {   // 向上
            dfs(board, i - 1, j, saveArray, flagArray, set);
        }
        if (i != xSize - 1 && board[i + 1][j] == 'O' && !saveArray[i + 1][j]) {    // 向下
            dfs(board, i + 1, j, saveArray, flagArray, set);
        }
        if (j != 0 && board[i][j - 1] == 'O' && !saveArray[i][j - 1]) {  // 向左
            dfs(board, i, j - 1, saveArray, flagArray, set);
        }
        if (j != ySize - 1 && board[i][j + 1] == 'O' && !saveArray[i][j + 1]) {    // 向右
            dfs(board, i, j + 1, saveArray, flagArray, set);
        }
    }

    public void solve(char[][] board) {
        if (0 == board.length)
            return;
        boolean[][] saveArray = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !saveArray[i][j]) {
                    boolean[] flagArray = new boolean[1];
                    Set<Integer> set = new HashSet<>();
                    dfs(board, i, j, saveArray, flagArray, set);
                    if (!flagArray[0]) {
                        for (int n : set) {
                            int ti = n / board[0].length;
                            int tj = n % board[0].length;
                            board[ti][tj] = 'X';
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'}, {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'}};
//        char[][] board = {{'O', 'X', 'O', 'O', 'X', 'X'}, {'O', 'X', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O', 'O'}, {'X', 'O', 'X', 'X', 'X', 'X'}, {'O', 'O', 'X', 'O', 'X', 'X'}, {'X', 'X', 'O', 'O', 'O', 'O'}};
//        char[][] board = {{'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'}};
//        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        P130 p130 = new P130();
        p130.solve(board);
        for (char[] chars : board) {
            System.out.println(chars);
        }

//        [["X","O","X","O","X","O","O","O","X","O"],
//        ["X","O","O","X","X","X","O","O","O","X"],
//        ["O","O","O","O","O","O","O","O","X","X"],
//        ["O","O","O","O","O","O","X","O","O","X"],
//        ["O","O","X","X","O","X","X","O","O","O"],
//        ["X","O","O","X","X","X","O","X","X","O"],
//        ["X","O","X","O","O","X","X","O","X","O"],
//        ["X","X","O","X","X","O","X","O","O","X"],
//        ["O","O","O","O","X","O","X","O","X","O"],
//        ["X","X","O","X","X","X","X","O","O","O"]]

//        [["X","O","X","O","X","O","O","O","X","O"],
//        ["X","O","O","X","X","X","O","O","O","X"],
//        ["O","O","O","O","O","O","O","O","X","X"],
//        ["O","O","O","O","O","O","X","O","O","X"],
//        ["O","O","X","X","O","X","X","O","O","O"],
//        ["X","O","O","X","X","X","X","X","X","O"],
//        ["X","O","X","X","-","X","X","O","X","O"],
//        ["X","X","O","X","X","X","X","O","-","X"],
//        ["O","O","O","-","X","X","X","O","X","O"],
//        ["X","X","O","X","X","X","X","O","O","O"]]

//        [["O","X","O","O","X","X"],
//        ["O","X","X","X","O","X"],
//        ["X","O","O","X","O","O"],
//        ["X","O","X","X","X","X"],
//        ["O","O","X","O","X","X"],
//        ["X","X","O","O","O","O"]]

//        [["O","X","O","O","X","X"],
//        ["O","X","X","X","O","X"],
//        ["X","-","-","X","O","O"],
//        ["X","-","X","X","X","X"],
//        ["O","O","X","O","X","X"],
//        ["X","X","O","O","O","O"]]

//        [["O","X","O","O","X","X"],
//        ["O","X","X","X","O","X"],
//        ["X","O","O","X","O","O"],
//        ["X","O","X","X","X","X"],
//        ["O","O","X","O","X","X"],
//        ["X","X","O","O","O","O"]]

//        [["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","X","X","X","X","O","O","O","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","O","O","X","O","X","O","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","O","X","O","X","O","O","O","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","O","O","O","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"]]

//        [["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","O","O","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
//        ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"]]
    }

}
