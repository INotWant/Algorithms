package other;

import java.util.ArrayList;
import java.util.List;

public class P119 {

    public List<Integer> getRow(int rowIndex) {
        int[][] array = new int[34][34];
        array[0][0] = 1;
        for (int i = 1; i < rowIndex + 1; i++) {
            array[i][0] = 1;
            for (int j = 1; j < i; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
            }
            array[i][i] = 1;
        }
        List<Integer> result = new ArrayList<>();
        if (rowIndex >= 0) {
            for (int i = 0; i < rowIndex + 1; i++) {
                result.add(array[rowIndex][i]);
            }
        }
        return result;
    }

}
