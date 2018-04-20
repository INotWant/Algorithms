package other;

public class P53 {

    /* // O(n)
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int temp = max < 0 ? 0 : max;
        for (int i = 1; i < nums.length; i++) {
            temp += nums[i];
            if (temp <= 0) {
                if (nums[i] > 0)
                    temp = nums[i];
                else
                    temp = 0;
                if (nums[i] > max)
                    max = nums[i];
            } else if (temp > max) {
                max = temp;
            }
        }
        return max;
    }
    */

    // O(n^2) 分治法
    public int maxSubArray(int[] nums) {
        return helper(0, nums.length - 1, nums);
    }

    private int helper(int start, int end, int[] nums) {
        if (start == end)
            return nums[start];
        if (start > end)
            return Integer.MIN_VALUE;
        int mid = (start + end) / 2;
        int m1 = helper(start, mid - 1, nums);
        int m2 = helper(mid + 1, end, nums);
        int m31 = 0, m32 = 0;
        int sum = 0;
        for (int i = mid - 1; i >= start; i--) {
            sum += nums[i];
            if (sum > m31)
                m31 = sum;
        }
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            if (sum > m32)
                m32 = sum;
        }
        int m3 = m31 + nums[mid] + m32;
        return m1 > m2 ? (m1 > m3 ? m1 : m3) : (m2 > m3 ? m2 : m3);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        P53 p53 = new P53();
        System.out.println(p53.maxSubArray(nums));
    }

}
