package other;

import java.util.Arrays;

/**
 * url :: https://leetcode.com/problems/search-for-a-range/description/
 *
 * @author kissx on 2018/2/24.
 */
public class P34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int start = 0;
        int end = nums.length - 1;
        int m = (start + end) / 2;
        int pos = -1;
        while (start <= end) {
            if (nums[m] == target) {
                pos = m;
                break;
            } else if (nums[m] > target)
                end = m - 1;
            else
                start = m + 1;
            m = (start + end) / 2;
        }
        if (pos == -1)
            return new int[]{-1, -1};
        // 1, get start
        int tempP;
        do {
            tempP = -1;
            start = 0;
            end = pos - 1;
            m = (start + end) / 2;
            while (start <= end) {
                if (nums[m] == target) {
                    tempP = m;
                    pos = m;
                    break;
                } else if (nums[m] > target)
                    end = m - 1;
                else
                    start = m + 1;
                m = (start + end) / 2;
            }
        } while (tempP != -1);
        int s = pos;
        // 2, get end
        do {
            tempP = -1;
            start = pos + 1;
            end = nums.length - 1;
            m = (start + end) / 2;
            while (start <= end) {
                if (nums[m] == target) {
                    tempP = m;
                    pos = m;
                    break;
                } else if (nums[m] > target)
                    end = m - 1;
                else
                    start = m + 1;
                m = (start + end) / 2;
            }
        } while (tempP != -1);
        int e = pos;
        return new int[]{s, e};
    }

    public static void main(String[] args) {
        P34 p34 = new P34();

//        int[] array = new int[]{5, 7, 7, 8, 8, 10};
//        int target = 8;

//        int[] array = new int[]{5, 7, 7, 8, 8, 10};
//        int target = 5;

        int[] array = new int[]{5, 5, 5, 5, 5, 5};
        int target = 5;

        int[] result = p34.searchRange(array, target);
        System.out.println(Arrays.toString(result));

    }

}
