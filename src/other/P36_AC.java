package other;

import java.util.HashMap;
import java.util.Map;

/**
 * url :: https://leetcode.com/problems/valid-sudoku/description/
 *
 * @author kissx on 2018/3/1.
 */
public class P36_AC {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Map<Character, Boolean>> maps = new HashMap<>();
        for (int i = 0; i < 9; i++)
            maps.put(i, new HashMap<>());
        for (int i = 10; i < 19; i++)
            maps.put(i, new HashMap<>());
        for (int i = 20; i < 29; i++)
            maps.put(i, new HashMap<>());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' != board[i][j]) {
                    // 行
                    Map<Character, Boolean> map1 = maps.get(i);
                    if (map1.get(board[i][j]) == null)
                        map1.put(board[i][j], true);
                    else
                        return false;
                    // 列
                    Map<Character, Boolean> map2 = maps.get(10 + j);
                    if (map2.get(board[i][j]) == null)
                        map2.put(board[i][j], true);
                    else
                        return false;
                    // 块
                    Map<Character, Boolean> map3 = maps.get(20 + (i / 3) * 3 + (j / 3));
                    if (map3.get(board[i][j]) == null)
                        map3.put(board[i][j], true);
                    else
                        return false;
                }
            }
        }
        return true;
    }
}
