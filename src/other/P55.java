package other;

public class P55 {

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + i > max) {
                max = nums[i] + i;
            }
            if (0 == nums[i] && max <= i)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P55 p55 = new P55();
//        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {3, 2, 1, 0, 4};
        int[] nums = {0};
        System.out.println(p55.canJump(nums));
    }

}
