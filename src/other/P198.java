package other;

/**
 * @author iwant
 * @date 2019-04-26 14:10
 */
public class P198 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] array = new int[nums.length];
        array[0] = nums[0];
        if (nums[1] > nums[0])
            array[1] = nums[1];
        else
            array[1] = nums[0];
        for (int i = 2; i < nums.length; i++)
            array[i] = Math.max(array[i - 1], array[i - 2] + nums[i]);
        return array[nums.length - 1];
    }

}
