package other;

import java.util.*;

public class P90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Boolean> map = new HashMap<>();
        Arrays.sort(nums);
        int num = 1 << nums.length;
        for (int i = 0; i < num; i++) {
            String binaryString = Integer.toBinaryString(i);
            List<Integer> list = new ArrayList<>();
            for (int j = binaryString.length() - 1; j >= 0; j--) {
                if (binaryString.charAt(j) == '1')
                    list.add(nums[binaryString.length() - 1 - j]);
            }
            if (!map.containsKey(list.toString())) {
                result.add(list);
                map.put(list.toString(), true);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        P90 p90 = new P90();
        int[] nums = {1};
        System.out.println(p90.subsetsWithDup(nums));
    }

}
