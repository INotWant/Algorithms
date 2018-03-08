package other;

/**
 * url :: https://leetcode.com/problems/trapping-rain-water/description/
 * 用二维数组来解决，感觉自己想出了一个巧妙的解决方案，然而 （314/315） TLE!
 *
 * @author kissx on 2018/3/8.
 */
public class P42_TLE {

    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int max = height[0];
        for (int i = 1; i < height.length; i++) {
            if (height[i] > max)
                max = height[i];
        }
        int[][] array = new int[max][height.length];
        for (int i = 0; i < height.length; i++) {
            int c = height[i];
            for (int j = 0; j < c; j++)
                array[j][i] = 2;
        }
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            boolean isHave = false;
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 2) {
                    if (isHave) {
                        number += count;
                        count = 0;
                    } else
                        isHave = !isHave;
                } else {
                    if (isHave)
                        ++count;
                }
            }
        }
        return number;
    }

    public static void main(String[] args) {
        P42_TLE p42TLE = new P42_TLE();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {2, 1, 0, 2};
        System.out.println(p42TLE.trap(height));

    }


}
