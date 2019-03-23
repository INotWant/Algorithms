package other;

public class P136 {

    public int singleNumber(int[] nums) {
        if (null == nums || 0 == nums.length)
            return 0;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }
        return sum;
    }

}
