package other;

import java.util.Arrays;

public class P48 {

    public void rotate(int[][] matrix) {
        int x, y;
        int temp1, temp2;
        int swap;
        int count, size;
        int s = 0;
        int e = matrix.length - 1;
        while (s < e) {
            size = 0;
            while (size < e - s) {
                x = s;
                y = s + size;
                temp1 = matrix[x][y];
                count = 1;
                while (count <= 4) {
                    swap = y;
                    y = matrix.length - 1 - x;
                    x = swap;
                    temp2 = matrix[x][y];
                    matrix[x][y] = temp1;
                    temp1 = temp2;
                    ++count;
                }
                ++size;
            }
            ++s;
            --e;
        }
    }

    public static void main(String[] args) {
        P48 p48 = new P48();
        /*
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        */
        int[][] matrix = {{1}};
        p48.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
