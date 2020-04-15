package other;

public class P304 {

    private static class NumMatrix {

        private int[][] sum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return;
            this.sum = new int[matrix.length][matrix[0].length];
            this.sum[0][0] = matrix[0][0];
            for (int i = 1; i < matrix[0].length; i++)
                this.sum[0][i] = matrix[0][i] + this.sum[0][i - 1];
            for (int i = 1; i < matrix.length; i++) {
                int tSum = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    tSum += matrix[i][j];
                    if (j == 0)
                        this.sum[i][0] = matrix[i][0] + this.sum[i - 1][0];
                    else
                        this.sum[i][j] = this.sum[i - 1][j] + tSum;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (sum == null)
                return 0;
            if (row1 == 0 && col1 == 0)
                return this.sum[row2][col2];
            else if (row1 == 0)
                return this.sum[row2][col2] - this.sum[row2][col1 - 1];
            else if (col1 == 0)
                return this.sum[row2][col2] - this.sum[row1 - 1][col2];
            return this.sum[row2][col2] - this.sum[row2][col1 - 1] - this.sum[row1 - 1][col2] + this.sum[row1 - 1][col1 - 1];
        }

    }

    public static void main(String[] args) {
        int[][] arrs = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix matrix = new NumMatrix(arrs);
        System.out.println(matrix.sumRegion(2, 1, 4, 3));
    }

}
