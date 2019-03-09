package other;

import java.util.HashMap;
import java.util.Map;

public class P128 {

    public int longestConsecutive(int[] nums) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = map.get(n - 1) == null ? 0 : map.get(n - 1);
                int right = map.get(n + 1) == null ? 0 : map.get(n + 1);
                int sum = left + right + 1;
                if (sum > result) {
                    result = sum;
                }
                map.put(n - left, sum);
                map.put(n + right, sum);
                map.put(n, sum);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P128 p128 = new P128();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(p128.longestConsecutive(nums));
    }

}
