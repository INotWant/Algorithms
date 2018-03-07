package other;

/**
 * url :: https://leetcode.com/problems/first-missing-positive/description/
 *
 * @author kissx on 2018/3/7.
 */
public class P41 {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        int slot = nums.length;
        int t1;
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            if (num == slot)
                nums[0] = num;
            else if (num < slot && num > 0) {
                t1 = nums[num];
                while (t1 != num) {
                    nums[num] = num;
                    if (t1 <= i && t1 > 0) {
                        nums[t1] = t1;
                        break;
                    } else if (t1 > 0 && t1 < slot) {
                        num = t1;
                        t1 = nums[t1];
                    } else if (t1 == slot) {
                        nums[0] = t1;
                        break;
                    } else
                        break;
                }
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i)
                return i;
        }
        if (nums[0] == nums.length)
            return nums.length + 1;
        return nums.length;
    }

    public static void main(String[] args) {
        P41 p41 = new P41();
//        int[] array = {1, 4, 9, 3, 10, 2};
//        int[] array = {1, 2, 0};
//        int[] array = {3, 4, -1, 1};
//        int[] array = {1, 1};
//        int[] array = {0, 2, 2, 1, 1};
        int[] array = {4, 3, 4, 1, 1, 4, 1, 4};
        System.out.println(p41.firstMissingPositive(array));
    }

}
