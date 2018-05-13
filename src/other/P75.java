package other;

import java.util.Arrays;

public class P75 {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int zeroPos = -1;
        int twoPos = nums.length;
        int num;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            if (num == 0) {
                if (i != zeroPos + 1) {
                    temp = nums[zeroPos + 1];
                    nums[zeroPos + 1] = 0;
                    nums[i] = temp;
                }
                ++zeroPos;
            } else if (num == 2) {
                if (i >= twoPos)
                    break;
                if (i != twoPos - 1) {
                    temp = nums[twoPos - 1];
                    nums[twoPos - 1] = 2;
                    nums[i] = temp;
                    --i;
                }
                --twoPos;
            }
        }
    }

    public static void main(String[] args) {
        P75 p75 = new P75();
//        int[] nums = {2, 0, 2, 1, 1, 0};
//        int[] nums = {0, 0, 0, 0};
        int[] nums = {2, 0};
        p75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

}
