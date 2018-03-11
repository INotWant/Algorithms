package other;

/**
 * url :: https://leetcode.com/problems/trapping-rain-water/description/
 * DP （边界、粗心！！！）
 *
 * @author kissx on 2018/3/11.
 */
public class P42_NewThink {

    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;
        int ans = 0;
        int[] array = new int[height.length];
        int cMax = height[0] > height[1] ? height[0] : height[1];
        int pos = height[0] > height[1] ? 0 : 1;
        if (pos == 0 && height[2] > height[1]) {
            ans += ((height[2] > height[0] ? height[0] : height[2]) - height[1]);
            array[1] = (height[2] > height[0] ? height[0] : height[2]) - height[1];
        }
        if (height[2] >= cMax) {
            cMax = height[2];
            pos = 2;
        }
        for (int i = 3; i < height.length; i++) {
            if (height[i] >= cMax) {
                for (int j = i - 1; j > pos; j--) {
                    int t = cMax - (array[j] + height[j]);
                    ans += t;
                    array[j] = t + array[j];
                }
                cMax = height[i];
                pos = i;
            } else {
                int j = i - 1;
                while (j > pos && height[j] < height[i]) {
                    int t = height[i] - (array[j] + height[j]);
                    ans += t;
                    array[j] = t + array[j];
                    j--;
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        P42_NewThink p42_newThink = new P42_NewThink();
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3};
//        int[] height = {0, 5, 6, 4, 6, 1, 0, 0, 2, 7};
//        int[] height = {3, 0, 5, 3, 3, 6, 5, 0, 3, 6};
        int[] height = {5, 3, 6};
//        int[] height = {5, 2, 1, 2, 1, 5};
        System.out.println(p42_newThink.trap(height));
    }

}
