package other;

import java.util.Arrays;

public class _leetcode_9_1 {

    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int count = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= count) {
                if (i == 0)
                    return count;
                else if (nums[i - 1] < count)
                    return count;
            }
            --count;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 5};
//        int[] arr = {0, 0};
//        int[] arr = {0, 4, 3, 0, 4};
        int[] arr = {3, 6, 7, 7, 0};
        System.out.println(specialArray(arr));
    }

}
