package other;

/**
 * @author iwant
 * @date 19-5-18 15:28
 */
public class P213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int t1 = nums[0] + helper(nums, nums.length - 2);
        int t2 = helper(nums, nums.length - 1);
        return Math.max(t1, t2);
    }

    private int helper(int[] nums, int end) {
        boolean[] flags = new boolean[nums.length];
        int[] moneys = new int[nums.length];
        flags[1] = end == nums.length - 1;
        moneys[1] = flags[1] ? nums[1] : 0;
        for (int i = 2; i <= end; i++) {
            if (!flags[i - 1]) {
                moneys[i] = moneys[i - 1] + nums[i];
                flags[i] = true;
            } else {
                int money = moneys[i - 2] + nums[i];
                if (money > moneys[i - 1]) {
                    moneys[i] = money;
                    flags[i] = true;
                } else {
                    moneys[i] = moneys[i - 1];
                    flags[i] = false;
                }
            }
        }
        return moneys[end];
    }

    public static void main(String[] args) {
        P213 p213 = new P213();
        int[] nums = {2, 4, 8, 9, 9, 3};
        System.out.println(p213.rob(nums));
    }

}

