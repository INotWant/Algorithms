package other;

import java.util.HashMap;
import java.util.Map;

public class P312 {

    public int maxCoins(int[] nums) {
        Map<String, Integer> saveMap = new HashMap<>();
        int[] arr = new int[nums.length + 2];
        arr[0] = arr[arr.length - 1] = 1;
        System.arraycopy(nums, 0, arr, 1, nums.length);
        return help(arr, 0, arr.length - 1, saveMap);
    }

    /**
     * DP 递归方式
     */
    private int help(int[] nums, int start, int end, Map<String, Integer> saveMap) {
        String key = start + "," + end;
        Integer result = saveMap.get(key);
        if (result != null)
            return result;

        if (start + 1 == end)
            return 0;
        if (start + 2 == end)
            return nums[start] * nums[start + 1] * nums[start + 2];
        else {
            int t1, t2, max = Integer.MIN_VALUE, tmp;
            for (int i = start + 1; i < end; i++) {
                t1 = help(nums, start, i, saveMap);
                t2 = help(nums, i, end, saveMap);
                tmp = t1 + t2 + nums[start] * nums[end] * nums[i];
                if (tmp > max)
                    max = tmp;
            }
            saveMap.put(key, max);
            return max;
        }
    }

}
