package other;

import java.util.ArrayList;
import java.util.List;

public class P78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        int specSize = (int) Math.pow(2, nums.length);
        for (int i = 0; i < specSize; i++) {
            int num = i;
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= nums.length; j++) {
                if ((num & 1) == 1) {
                    list.add(nums[j - 1]);
                }
                num >>= 1;
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        P78 p78 = new P78();
//        System.out.println(p78.subsets(new int[]{1, 2, 3}));
        System.out.println(p78.subsets(new int[]{}));
    }

}
