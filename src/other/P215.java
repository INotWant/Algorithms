package other;

import java.util.Random;

/**
 * @author iwant
 * @date 19-5-18 16:14
 */
public class P215 {

    private Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0];
        return helper(nums, nums.length + 1 - k, 0, nums.length - 1);
    }

    private int helper(int[] nums, int k, int start, int end) {
        if (start == end)
            return nums[start];
        int random = this.random.nextInt(end - start + 1);
        random += start;
        int temp = nums[random];
        nums[random] = nums[start];
        nums[start] = temp;
        int i = start, j = end;
        while (i < j) {
            while (i < j && nums[i] <= temp)
                ++i;
            if (i < j) {
                nums[start] = nums[j];
                nums[j--] = nums[i];
                nums[i] = nums[start];
            }
            while (j > i && nums[j] >= temp)
                --j;
            if (j > i) {
                nums[start] = nums[i];
                nums[i++] = nums[j];
                nums[j] = nums[start];
            }
        }
        if (nums[i] > temp)
            --i;
        nums[start] = nums[i];
        nums[i] = temp;
        if (k == i + 1)
            return nums[i];
        if (k > i + 1)
            return helper(nums, k, i + 1, end);
        else
            return helper(nums, k, start, i - 1);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        P215 p215 = new P215();
        System.out.println(p215.findKthLargest(nums, 2));
    }

}
