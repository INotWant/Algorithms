package other;

import java.util.*;

/**
 * 读错题意了，，，最终实现了 “判断数独是否有解的算法”。原题为判断当前部分数独是否合法！【无心插柳】
 *
 * @author kissx on 2018/2/26.
 */
public class P36 {

    private static class Data {
        int x;
        int y;

        Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final Map<Integer, Data> map = new HashMap<>();

    static {
        map.put(0, new Data(0, 0));
        map.put(1, new Data(0, 3));
        map.put(2, new Data(0, 6));
        map.put(3, new Data(3, 0));
        map.put(4, new Data(3, 3));
        map.put(5, new Data(3, 6));
        map.put(6, new Data(6, 0));
        map.put(7, new Data(6, 3));
        map.put(8, new Data(6, 6));
    }

    public boolean isValidSudoku(char[][] board) {
        return isValidSudokuHelp(board);
    }

    private static boolean isValidSudokuHelp(char[][] board) {
        int[] unknownCountArray = new int[9];
        int unknownCount = 0;
        int count = 0;
        for (int i = 0; i < 9; ) {
            for (int j = 0; j < 9; ) {
                unknownCountArray[count++] = 9 - findInBlock(i, j, board).size();
                unknownCount += unknownCountArray[count - 1];
                j += 3;
            }
            i += 3;
        }
        if (unknownCount == 0) {
            //*
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    System.out.print("| " + board[i][j] + " |");
                System.out.println();
            }
            // */
            return true;
        }
        int minBlock = findMinBlock(unknownCountArray);
        int startX = map.get(minBlock).x;
        int startY = map.get(minBlock).y;
        List<Character> knowList = findInBlock(startX, startY, board);
        List<String> permutationList = getPermutations(knowList);
        for (String str : permutationList) {
            char[][] copy = new char[9][9];
            for (int i = 0; i < 9; i++)
                copy[i] = Arrays.copyOf(board[i], board.length);
            char[] chars = new char[str.length()];
            count = 0;
            for (int i = 0; i < str.length(); i++)
                chars[count++] = str.charAt(i);
            count = 0;
            boolean isSuccess = true;
            for (int i = startX; i < startX + 3 && isSuccess; i++) {
                for (int j = startY; j < startY + 3 && isSuccess; j++) {
                    if ('.' == board[i][j]) {
                        char c = chars[count];
                        if (isHaveInColumn(c, board, j) || isHaveInRow(c, board, i))
                            isSuccess = false;
                        else
                            copy[i][j] = c;
                        ++count;
                    }
                }
            }
            if (isSuccess) {
                if (isValidSudokuHelp(copy))
                    return true;
            }
        }
        return false;
    }


    private static List<String> getPermutations(List<Character> knowList) {
        char[] unknownArray = new char[9 - knowList.size()];
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (!knowList.contains((char) ('0' + i)))
                unknownArray[count++] = (char) ('0' + i);
        }
        return permutationsHelp(unknownArray);
    }

    // 全排列算法
    private static List<String> permutationsHelp(char[] unknownArray) {
        List<String> result = new ArrayList<>();
        if (unknownArray.length > 2) {
            for (int i = 0; i < unknownArray.length; i++) {
                char temp = unknownArray[0];
                unknownArray[0] = unknownArray[i];
                unknownArray[i] = temp;
                List<String> mList = permutationsHelp(Arrays.copyOfRange(unknownArray, 1, unknownArray.length));
                for (String str : mList)
                    result.add(unknownArray[0] + str);
            }
        } else if (unknownArray.length == 2) {
            result.add(unknownArray[0] + "" + unknownArray[1]);
            result.add(unknownArray[1] + "" + unknownArray[0]);
        } else if (unknownArray.length == 1)
            result.add(String.valueOf(unknownArray[0]));
        return result;
    }


    private static int findMinBlock(int[] unknownCountArray) {
        int min = unknownCountArray[0];
        int p = 0;
        for (int i = 1; i < 9; i++) {
            if (min == 0 || (unknownCountArray[i] != 0 && unknownCountArray[i] < min)) {
                min = unknownCountArray[i];
                p = i;
            }
        }
        return p;
    }

    private static List<Character> findInBlock(int x, int y, char[][] board) {
        List<Character> result = new ArrayList<>();
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = startX; i < startX + 3; i++)
            for (int j = startY; j < startY + 3; j++)
                if ('.' != board[i][j])
                    result.add(board[i][j]);
        return result;
    }

    private static boolean isHaveInRow(char c, char[][] board, int row) {
        for (int i = 0; i < 9; i++)
            if (c == board[row][i])
                return true;
        return false;
    }

    private static boolean isHaveInColumn(char c, char[][] board, int column) {
        for (int i = 0; i < 9; i++)
            if (c == board[i][column])
                return true;
        return false;
    }

    public static void main(String[] args) {
        /*
        char[] chars = new char[]{'1', '2', '3', '4'};
        List<String> list = permutationsHelp(chars);
        for (String str : list)
            System.out.println(str);
        */
        /*
        char[][] board = {{'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}};
        //*/
        /*
        char[][] board = {{'.', '6', '.', '5', '9', '3', '.', '.', '.'},
                {'9', '.', '1', '.', '.', '.', '5', '.', '.'},
                {'.', '3', '.', '4', '.', '.', '.', '9', '.'},
                {'1', '.', '8', '.', '2', '.', '.', '.', '4'},
                {'4', '.', '.', '3', '.', '9', '.', '.', '1'},
                {'2', '.', '.', '.', '1', '.', '6', '.', '9'},
                {'.', '8', '.', '.', '.', '6', '.', '2', '.'},
                {'.', '.', '4', '.', '.', '.', '8', '.', '7'},
                {'.', '.', '.', '7', '8', '5', '.', '1', '.'}};
        //*/

        /*
        char[][] board = {{'.', '.', '.', '.', '7', '.', '5', '.', '8'},
                {'5', '4', '.', '.', '1', '.', '.', '7', '.'},
                {'2', '.', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '1', '.', '.', '.', '2', '6'},
                {'.', '.', '.', '.', '6', '.', '.', '.', '.'},
                {'1', '5', '.', '.', '.', '4', '3', '.', '.'},
                {'.', '.', '.', '.', '.', '9', '.', '.', '4'},
                {'.', '1', '.', '.', '4', '.', '.', '6', '3'},
                {'6', '.', '4', '.', '2', '.', '.', '.', '.'}};
                //*/

        char[][] board =
                {
                        {'.', '.', '.', '.', '5', '7', '.', '8', '.'},
                        {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '2', '9', '.', '.', '4', '.', '.'},
                        {'2', '.', '.', '8', '.', '9', '3', '.', '.'},
                        {'4', '.', '.', '.', '.', '.', '.', '.', '7'},
                        {'.', '.', '7', '4', '.', '6', '.', '.', '8'},
                        {'.', '.', '6', '.', '.', '2', '7', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '5'},
                        {'.', '9', '.', '3', '1', '.', '.', '.', '.'}
                };

        P36 p36 = new P36();
        long start = System.currentTimeMillis();
        boolean result = p36.isValidSudoku(board);
        long end = System.currentTimeMillis();
        System.out.println("Total time :: " + (end - start) + "ms");
        System.out.println("Result ::  " + result);
    }

}
