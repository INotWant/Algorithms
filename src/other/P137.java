package other;

import java.util.HashMap;
import java.util.Map;

public class P137 {

    // 方法一
    /*
    public int singleNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            Integer count = map.get(n);
            if (count == null) {
                count = 1;
            } else {
                ++count;
            }
            map.put(n, count);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }
    // */

    // 方法二 --> 自己想不出来
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for(int num : nums){
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }
}