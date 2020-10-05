package other;

import java.util.Arrays;

public class P240 {

//    public boolean searchMatrix(int[][] matrix, int target) {
//        for(int[] arr : matrix){
//            int index = Arrays.binarySearch(arr, target);
//            if (index >= 0)
//                return true;
//        }
//        return false;
//    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;

        while (i < matrix.length && j >= 0){
            if (matrix[i][j] > target){
                --j;
            }else if (matrix[i][j] < target){
                ++i;
            }else
                return true;
        }
        return false;
    }

}
