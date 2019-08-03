package other;

/**
 * @author iwant
 * @date 19-6-26 19:22
 * @desc
 */
public class P221 {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int maxLen = 0;
        int[] lastArray = new int[matrix[0].length];
        int[] array;

        for (int i = 0; i < matrix[0].length; i++) {
            lastArray[i] = matrix[0][i] == '1' ? 1 : 0;
            if (lastArray[i] == 1)
                maxLen = 1;
        }

        for (int i = 1; i < matrix.length; i++) {
            array = new int[matrix[0].length];
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0')
                    array[j] = 0;
                else if (matrix[i][j] == '1') {
                    if (j == 0)
                        array[j] = 1;
                    else
                        array[j] = Math.min(Math.min(array[j - 1], lastArray[j - 1]), lastArray[j]) + 1;
                    if (array[j] > maxLen)
                        maxLen = array[j];
                }
            }
            lastArray = array;
        }

        return maxLen * maxLen;
    }

}
