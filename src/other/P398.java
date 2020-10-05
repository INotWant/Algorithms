package other;

import java.util.*;

public class P398 {

    static class Solution {

        private Map<Integer, Pair<List<Integer>, Integer>> numIndexMap = new HashMap<>();

        public Solution(int[] nums) {
            int i = 0;
            for (int num : nums) {
                numIndexMap.putIfAbsent(num, new Pair<>(new ArrayList<>(), 0));
                Pair<List<Integer>, Integer> pair = numIndexMap.get(num);
                pair.getFirst().add(i++);
            }
        }

        public int pick(int target) {
            Pair<List<Integer>, Integer> pair = numIndexMap.get(target);
            List<Integer> list = pair.getFirst();
            int index = pair.getSecond();
            int result = list.get(index);
            pair.setSecond(index + 1 == list.size() ? 0 : index + 1);
            return result;
        }

    }

}
