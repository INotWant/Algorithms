package other;

/**
 * url :: https://leetcode.com/problems/container-with-most-water/description/
 *
 * @author kissx on 2018/2/5.
 */
public class P11 {

    /* O(n^2)  Time Limit
    public static int maxArea(int[] height) {
        int max = 0;
        int mWidth = 0;
        int mLength = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int length = height[i] > height[j] ? height[j] : height[i];
                if (width < mWidth && length < mLength)
                    continue;
                int area = width * length;
                if (area > max) {
                    max = area;
                    mWidth = width;
                    mLength = length;
                }
            }
        }
        return max;
    }
    // */

    // O(n)
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        for (int i = 0; i < height.length - 1; i++) {
            int width = height[l] > height[r] ? height[r--] : height[l++];
            int length = r - l + 1;
            int area = width * length;
            if (area > maxArea)
                maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }


}
