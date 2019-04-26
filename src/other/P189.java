package other;

public class P189 {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[len - 1];
            System.arraycopy(nums, 0, nums, 1, len - 1);
            nums[0] = temp;
        }
    }

}
