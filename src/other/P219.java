package other;

/**
 * @author iwant
 * @date 19-6-24 19:01
 * @desc
 */
public class P219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;

        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j <= i + k && j < nums.length; j++)
                if (nums[i] == nums[j])
                    return true;
        return false;
    }

}
