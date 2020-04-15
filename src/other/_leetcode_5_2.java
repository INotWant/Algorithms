package other;

import java.util.HashMap;
import java.util.Map;

public class _leetcode_5_2 {


    public int numRollsToTarget(int d, int f, int target) {
        return (int) (helper(d, f, target) % 1000000007);
    }

    private Map<String, Long> map = new HashMap<>();

    private long helper(int d, int f, int target) {
        if (target <= 0)
            return 0;
        if (d == 1)
            if (target > f)
                return 0;
            else
                return 1;
        else {
            String key = d + "," + f + "," + target;
            Long result = map.get(key);
            if (result != null)
                return result;
            long totalNum = 0;
            for (int i = 1; i <= f; i++)
                totalNum += numRollsToTarget(d - 1, f, target - i);
            map.put(key, totalNum);
            return totalNum;
        }
    }

    public static void main(String[] args) {
        _leetcode_5_2 obj = new _leetcode_5_2();
        System.out.println(obj.numRollsToTarget(30, 30, 500));
    }

}
