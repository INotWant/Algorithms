package other;

import java.util.*;

/**
 * url :: https://leetcode.com/problems/4sum/description/
 *
 * @author kissx on 2018/2/10.
 */
public class P18 {

    /* TLE O(n^3) :: 将 4 Sum 转化为 n 个 3 Sum
    // however, we call do better by this method.
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Boolean> iMap = new HashMap<>();
        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (iMap.get(nums[i]) == null) {
                int newTarget = target - nums[i];
                result.addAll(threeSum(nums, i, newTarget, map));
                iMap.put(nums[i], true);
            }
        }
        return result;
    }

    private List<List<Integer>> threeSum(int[] nums, int removePos, int target, Map<String, Boolean> map) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == removePos)
                continue;
            int a = nums[i];
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (j == removePos) {
                    ++j;
                    continue;
                }
                if (k == removePos) {
                    --k;
                    continue;
                }
                int b = nums[j];
                int c = nums[k];
                int threeSum = a + b + c;
                String str;
                if (removePos < i)
                    str = nums[removePos] + "," + a + "," + b;
                else if (removePos < j)
                    str = a + "," + nums[removePos] + "," + b;
                else if (removePos < k)
                    str = a + "," + b + "," + nums[removePos];
                else
                    str = a + "," + b + "," + c;
                if (target == threeSum && map.get(str) == null) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    list.add(nums[removePos]);
                    result.add(list);
                    map.put(str, true);
                    while (j < nums.length && nums[j] == b)
                        ++j;
                    while (k > 0 && nums[k] == c)
                        --k;
                } else if (target > threeSum)
                    while (j < nums.length && nums[j] == b)
                        ++j;
                else
                    while (k > 0 && nums[k] == c)
                        --k;
            }
        }
        return result;
    }
    // */


    // O(n^2) --> Accepted (Not a good idea, but it gives a new think.)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Integer, String> map = new HashMap<>();
        // 记录某个数出现的次数，用于处理一个数两次用问题
        Map<Integer, Integer> cMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];

            Integer count = cMap.get(a);
            count = count == null ? 1 : ++count;
            cMap.put(a, count);

            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                String str = a + "," + b + ";";
                String lastStr = map.get(a + b);
                if (lastStr != null) {
                    if (!lastStr.contains(str))
                        map.put(a + b, lastStr + str);
                } else
                    map.put(a + b, str);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Boolean> vMap = new HashMap<>();
        Map<String, Boolean> vvMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer a = entry.getKey();
            if (vMap.get(a) != null)
                continue;
            int b = target - a;
            String bStr = map.get(b);
            if (bStr != null) {
                vMap.put(a, true);
                vMap.put(b, true);
                String[] aStrs = entry.getValue().split(";");
                String[] bStrs = bStr.split(";");
                for (String aStr : aStrs) {
                    String[] strA = aStr.split(",");
                    int ra = Integer.parseInt(strA[0]);
                    int rb = Integer.parseInt(strA[1]);
                    for (String bStr1 : bStrs) {
                        String[] strB = bStr1.split(",");
                        int rc = Integer.parseInt(strB[0]);
                        int rd = Integer.parseInt(strB[1]);

                        int[] tArray = {ra, rb, rc, rd};
                        Arrays.sort(tArray);
                        int raa = tArray[0];
                        int rbb = tArray[1];
                        int rcc = tArray[2];
                        int rdd = tArray[3];
                        String str;
                        str = raa + "," + rbb + "," + rcc;

                        if (vvMap.get(str) == null) {
                            List<Integer> list = new ArrayList<>();
                            list.add(raa);
                            list.add(rbb);
                            list.add(rcc);
                            list.add(rdd);
                            result.add(list);
                            vvMap.put(str, true);
                        }
                    }
                }
            }
        }
        for (int i = result.size() - 1; i >= 0; i--) {
            List<Integer> list = result.get(i);
            int a = list.get(0);
            int b = list.get(1);
            int c = list.get(2);
            int d = list.get(3);
            if (a == b && b == c && c == d) {
                if (cMap.get(a) < 4)
                    result.remove(i);
            } else if (b == c && c == d) {
                if (cMap.get(b) < 3)
                    result.remove(i);
            } else if (a == b && b == c) {
                if (cMap.get(a) < 3)
                    result.remove(i);
            } else if (a == b && c == d) {
                if (cMap.get(a) < 2 || cMap.get(c) < 2)
                    result.remove(i);
            } else if (a == b) {
                if (cMap.get(a) < 2)
                    result.remove(i);
            } else if (b == c) {
                if (cMap.get(b) < 2)
                    result.remove(i);
            } else if (c == d) {
                if (cMap.get(c) < 2)
                    result.remove(i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        P18 p18 = new P18();
//        int[] nums = {1, 0, -1, 0, -2, 2};
//        int target = 0;

//        int[] nums = {-497, -494, -484, -477, -453, -453, -444, -442, -428, -420, -401, -393, -392, -381, -357, -357, -327, -323, -306, -285, -284, -263, -262, -254, -243, -234, -208, -170, -166, -162, -158, -136, -133, -130, -119, -114, -101, -100, -86, -66, -65, -6, 1, 3, 4, 11, 69, 77, 78, 107, 108, 108, 121, 123, 136, 137, 151, 153, 155, 166, 170, 175, 179, 211, 230, 251, 255, 266, 288, 306, 308, 310, 314, 321, 322, 331, 333, 334, 347, 349, 356, 357, 360, 361, 361, 367, 375, 378, 387, 387, 408, 414, 421, 435, 439, 440, 441, 470, 492};
//        int target = 1682;


        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        long start = System.currentTimeMillis();
        List<List<Integer>> result = p18.fourSum(nums, target);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("Time :: " + (end - start));
    }
}
