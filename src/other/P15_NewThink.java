package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kissx on 2018/2/8.
 */
public class P15_NewThink {

    /*
    public List<List<Integer>> threeSum(int[] num) {
        int a, b, c;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(num);
        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0; i <= num.length - 3; i++) {
            a = num[i];
            if (a > 0)
                break;
            for (int j = i + 1, k = num.length - 1; j < k; ) {
                b = num[j];
                c = num[k];
                String str = a + "," + b;
                if (b + c == -1 * a && map.get(str) == null) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    result.add(list);
                    j++;
                    k--;
                    map.put(str, true);
                } else if (b + c < -1 * a) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
    // */

    // 对上面的代码进行了优化，求解问题的思路相同，但是在去重时此处更完美。
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            result = twoSum(nums, i + 1, nums.length - 1, nums[i], result);
        }
        return result;
    }

    private List<List<Integer>> twoSum(int[] nums, int start, int end, int first, List<List<Integer>> result) {
        while (start < end) {
            int sum = first + nums[start] + nums[end];
            if (sum == 0) {
                List<Integer> item = new ArrayList<>();
                item.add(first);
                item.add(nums[start]);
                item.add(nums[end]);
                result.add(item);
                //跳过重复的数据
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                start++;
                end--;
                continue;
            }
            if (sum < 0) {
                //跳过重复的数据
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;
            }
            if (sum > 0) {
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                end--;
            }
        }
        return result;
    }

}
