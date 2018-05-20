package other;

import java.util.*;

public class P79 {

    public boolean exist(char[][] board, String word) {
        Map<Character, List<Integer>> posMap = new HashMap<>();
        int count = 0;
        int rowSize = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < rowSize; j++) {
                char c = board[i][j];
                List<Integer> posList = posMap.get(c);
                if (posList == null)
                    posList = new ArrayList<>();
                posList.add(count);
                posMap.put(c, posList);
                ++count;
            }
        }
        Set<Integer> watchedSet = new HashSet<>();
        return helper(watchedSet, posMap, word, 0, rowSize, -1);
    }

    private static boolean helper(Set<Integer> watchedSet, Map<Character, List<Integer>> posMap, String word, int index, int rowSize, int lastPos) {
        if (index >= word.length())
            return true;
        char c = word.charAt(index);
        List<Integer> listPos = posMap.get(c);
        if (listPos != null) {
            for (int pos : listPos) {
                boolean subResult = false;
                if (!watchedSet.contains(pos)) {
                    if (lastPos == -1 || Math.abs(pos - lastPos) == 1 || Math.abs(pos - lastPos) == rowSize) {
                        if (pos != 0 && (pos - lastPos == 1 && pos % rowSize == 0) || (pos - lastPos == -1 && lastPos % rowSize == 0))
                            continue;
                        watchedSet.add(pos);
                        subResult = helper(watchedSet, posMap, word, index + 1, rowSize, pos);
                        if (!subResult)
                            watchedSet.remove(pos);
                    }
                }
                if (subResult)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P79 p79 = new P79();

//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        System.out.println(p79.exist(board, "ABCCED"));
//        System.out.println(p79.exist(board, "SEE"));
//        System.out.println(p79.exist(board, "ABCB"));

//        char[][] board = {{'a'}};
//        System.out.println(p79.exist(board, "ab"));

//        char[][] board = {
//                {'C', 'A', 'A'},
//                {'A', 'A', 'A'},
//                {'B', 'C', 'D'}
//        };
//        System.out.println(p79.exist(board, "AAB"));

//        char[][] board = {{'a', 'a'}};
//        System.out.println(p79.exist(board, "aaa"));

    }

}
