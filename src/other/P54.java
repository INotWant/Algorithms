package other;

import java.util.ArrayList;
import java.util.List;

public class P54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int n = 0;
        int row = matrix.length;
        if (row == 0)
            return result;
        int column = matrix[0].length;
        while (result.size() < row * column) {
            for (int i = n; i < column - 1 - n; i++) {
                if (result.size() == row * column)
                    break;
                result.add(matrix[n][i]);
            }
            for (int i = n; i < row - 1 - n; i++) {
                if (result.size() == row * column)
                    break;
                result.add(matrix[i][column - 1 - n]);
            }
            for (int i = column - 1 - n; i > n; i--) {
                if (result.size() == row * column)
                    break;
                result.add(matrix[row - 1 - n][i]);
            }
            for (int i = row - 1 - n; i > n; i--) {
                if (result.size() == row * column)
                    break;
                result.add(matrix[i][n]);
            }
            ++n;
            if (result.size() == row * column - 1)
                result.add(matrix[row / 2][column / 2]);
        }
        return result;
    }

    public static void main(String[] args) {
        P54 p54 = new P54();
        /*
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // */
        //*
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        // */
        /*
        int[][] matrix = {
                {1, 2, 3, 4}
        };
        // */
        System.out.println(p54.spiralOrder(matrix));
    }

}
