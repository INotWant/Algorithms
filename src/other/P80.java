package other;

import java.util.Arrays;

public class P80 {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int lastNum = nums[0];
        int count = 1;
        int length = 1;
        int num;
        for (int i = 1; i < nums.length; i++) {
            num = nums[i];
            if (num == lastNum) {
                ++count;
            } else {
                count = 1;
                lastNum = num;
            }
            if (count <= 2) {
                ++length;
                if (i != length - 1)
                    nums[length - 1] = nums[i];
            }
        }
        return length;
    }

    public static void main(String[] args) {
        P80 p80 = new P80();

        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        System.out.println(p80.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

}
