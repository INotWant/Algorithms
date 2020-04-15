package other;

import java.util.HashMap;
import java.util.Map;

public class P1218 {

    public int longestSubsequence(int[] arr, int difference) {
        int maxNum = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            int tmp = a - difference;
            if (map.containsKey(tmp)) {
                int count = map.get(tmp);
                ++count;
                if (count > maxNum)
                    maxNum = count;
                map.put(a, count);
                map.remove(tmp);
            } else {
                if (!map.containsKey(a))
                    map.put(a, 1);
            }
        }
        return maxNum;
    }

}
