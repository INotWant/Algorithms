package other;

public class _leetcode_4_1 {

    public int movesToMakeZigzag(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] copyNums = new int[nums.length];
        System.arraycopy(nums, 0, copyNums, 0, nums.length);

        int count1 = 0;
        boolean flag = true;
        int t;
        for (int i = 1; i < nums.length; i++) {
            if (flag) {
                if (nums[i] <= nums[i - 1]) {
                    t = nums[i - 1] - nums[i] + 1;
                    nums[i - 1] -= t;
                    count1 += t;
                }
            } else {
                if (nums[i] >= nums[i - 1]) {
                    t = nums[i] - nums[i - 1] + 1;
                    nums[i] -= t;
                    count1 += t;
                }
            }
            flag = !flag;
        }

        int count2 = 0;
        flag = true;
        for (int i = 1; i < copyNums.length; i++) {
            if (!flag) {
                if (copyNums[i] <= copyNums[i - 1]) {
                    t = copyNums[i - 1] - copyNums[i] + 1;
                    copyNums[i - 1] -= t;
                    count2 += t;
                }
            } else {
                if (copyNums[i] >= copyNums[i - 1]) {
                    t = copyNums[i] - copyNums[i - 1] + 1;
                    copyNums[i] -= t;
                    count2 += t;
                }
            }
            flag = !flag;
        }

        return Math.min(count1, count2);
    }

    public static void main(String[] args) {
        _leetcode_4_1 obj = new _leetcode_4_1();
        int[] nums = {3, 1, 7, 4, 4, 1, 1, 10, 10, 9};
        System.out.println(obj.movesToMakeZigzag(nums));
    }

}
