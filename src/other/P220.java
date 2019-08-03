package other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author iwant
 * @date 19-6-24 19:11
 * @desc 认知：动态集合、TreeSet 平衡树
 */
public class P220 {

    // 官方解题思路一
    // NB 对数据结构 Java 太了解了
    /*
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || nums.length == 1)
            return false;

        // 平衡树
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceiling = treeSet.ceiling((long) nums[i]);
            if (ceiling != null && Math.abs(ceiling - nums[i]) <= t)
                return true;
            Long floor = treeSet.floor((long) nums[i]);
            if (floor != null && Math.abs(floor - nums[i]) <= t)
                return true;
            treeSet.add((long) nums[i]);

            if (treeSet.size() > k)
                treeSet.remove((long) nums[i - k]);
        }
        return false;
    }
    // */






    // 官方解题思路二：以 t 为桶！！！
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in nei***or buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

}
