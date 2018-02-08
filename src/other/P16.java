package other;

import java.util.Arrays;

/**
 * url :: https://leetcode.com/problems/3sum-closest/description/
 *
 * @author kissx on 2018/2/8.
 */
public class P16 {
    /* Error :: 总体思路正确，但太想着优化了！
    public static int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        int llSum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int a, b, c;
        for (int i = 0; i < nums.length - 2; i++) {
            a = nums[i];
            int lSum = a + nums[i + 1] + nums[nums.length - 1];
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                b = nums[j];
                c = nums[k];
                int tSum = a + b + c;
                if ((tSum - target > 0 && lSum - target > 0) || (tSum - target < 0 && lSum - target < 0)) {
                    if (Math.abs(tSum - target) > Math.abs(lSum - target)) {
                        if (Math.abs(target - lSum) < Math.abs(target - result))
                            result = lSum;
                        break;
                    }
                } else
                    llSum = lSum;
                if (tSum == target)
                    return target;
                else if (tSum > target)
                    --k;
                else
                    ++j;
                lSum = tSum;
            }
            if (Math.abs(target - lSum) < Math.abs(target - result))
                result = lSum;
            if (Math.abs(target - llSum) < Math.abs(target - result))
                result = llSum;
        }
        return result;
    }
    // */

    public static int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int a, b, c;
        for (int i = 0; i < nums.length - 2; i++) {
            a = nums[i];
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                b = nums[j];
                c = nums[k];
                int tSum = a + b + c;
                if (tSum == target)
                    return target;
                else if (tSum > target)
                    --k;
                else
                    ++j;
                if (Math.abs(target - tSum) < Math.abs(target - result))
                    result = tSum;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33};
        int target = 0;

//        int[] nums = {1, 2, 5, 10, 11};
//        int target = 12;

//        int[] nums = {1, 6, 9, 14, 16, 70};
//        int target = 81;

        System.out.println(threeSumClosest(nums, target));
    }

}
