package other;

/**
 * url :: https://leetcode.com/problems/remove-element/description/
 *
 * @author kissx on 2018/2/17.
 */
public class P27 {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int counter = nums.length - 1;
        for (int i = 0; i < counter; i++) {
            if (nums[i] == val) {
                int temp = nums[counter];
                nums[counter] = val;
                nums[i] = temp;
                --i;
                --counter;
            }
        }
        counter = val == nums[counter] ? --counter : counter;
        return counter + 1;
    }

}
