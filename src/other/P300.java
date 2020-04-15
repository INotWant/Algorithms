package other;

public class P300 {

    // 经典的 LIS(Longest Increasing Subsequence) 问题

    // 方法一：暴力搜索
    // 虽然会 TLE，但是递归思想很清晰
    /*
    public int lengthOfLIS(int[] nums) {
        return helper(nums, Integer.MIN_VALUE, 0);
    }

    private int helper(int[] nums, int preValue, int curr) {
        if (curr >= nums.length)
            return 0;
        int num = 0;
        if (preValue < nums[curr])
            num = 1 + helper(nums, nums[curr], curr + 1);
        return Math.max(num, helper(nums, preValue, curr + 1));
    }
    */

    // 对方法一的改进
    /*
    public int lengthOfLIS(int[] nums) {
        int[][] save = new int[nums.length + 1][nums.length];
        for (int[] arr : save)
            Arrays.fill(arr, -1);
        return helper(nums, -1, 0, save);
    }

    private int helper(int[] nums, int preIndex, int currIndex, int[][] save) {
        if (currIndex >= nums.length)
            return 0;
        if (save[preIndex + 1][currIndex] != -1)
            return save[preIndex + 1][currIndex];
        int num1 = 0;
        if (preIndex < 0 || nums[currIndex] > nums[preIndex])
            num1 = 1 + helper(nums, currIndex, currIndex + 1, save);
        int num2 = helper(nums, preIndex, currIndex + 1, save);
        save[preIndex + 1][currIndex] = Math.max(num1, num2);
        return Math.max(num1, num2);
    }
    */

    // 方法二：动态规划
    /*
    int max = 0;

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        // dp[i] 表示从 0～i 含 i-th 的最长上升子序列

        for (int i = 0; i < nums.length; i++) {
            int tMax = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int t = dp[j] + 1;
                    if (t > tMax)
                        tMax = t;
                }
            }
            dp[i] = tMax;
            if (tMax > max)
                max = tMax;
        }

        return max;
    }
    */

    // 方法三
    public int lengthOfLIS(int[] nums) {
        if (nums == null)
            return 0;
        int[] arr = new int[nums.length];
        int index = -1;
        for (int num : nums) {
            if (index == -1)
                arr[++index] = num;
            else {
                if (num > arr[index])
                    arr[++index] = num;
                else {
                    int pos = getFirstBig(arr, index, num);
                    arr[pos] = num;
                }
            }
        }
        return index + 1;
    }

    private int getFirstBig(int[] arr, int e, int n) {
        int i = 0;
        int j = e;
        while (i < j) {
            int mid = (i + j) / 2;
            if (arr[mid] == n)
                return mid;
            else if (arr[mid] < n)
                i = mid + 1;
            else
                j = mid;
        }
        return i;
    }

    public static void main(String[] args) {
        P300 obj = new P300();
        int[] nums = {4, 10, 4, 3, 8, 9};
        System.out.println(obj.lengthOfLIS(nums));
    }
}
