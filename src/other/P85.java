package other;

public class P85 {

    public int maximalRectangle(char[][] matrix) {
        int area = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int tArea = part(i, j, matrix);
                    if (tArea > area)
                        area = tArea;
                }
            }
        }
        return area;
    }

    private int part(int i, int j, char[][] matrix) {
        int area = 0;
        int column = j;
        int min = matrix.length - i;
        while (column < matrix[i].length && matrix[i][column] == '1') {
            int count = column - j + 1;

            int row = i;
            int num = 0;
            while (row < matrix.length && matrix[row][column] == '1') {
                ++num;
                ++row;
            }
            if (num < min)
                min = num;
            if (min * count > area)
                area = min * count;
            ++column;
        }
        return area;
    }

    public static void main(String[] args) {
        P85 p85 = new P85();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(p85.maximalRectangle(matrix));
    }


}
