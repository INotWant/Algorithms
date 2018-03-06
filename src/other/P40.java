package other;

import java.util.*;

/**
 * url :: https://leetcode.com/problems/combination-sum-ii/description/
 * DP -> 类似 39
 *
 * @author kissx on 2018/3/6.
 */
public class P40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int index = Arrays.binarySearch(candidates, target);
        index = index < 0 ? -1 * index - 2 : index;
        Map<String, Boolean> verifyMap = new HashMap<>();
        List<List<Integer>> cResult = helper(index, candidates, target);
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : cResult) {
            if (verifyMap.get(list.toString()) == null) {
                result.add(list);
                verifyMap.put(list.toString(), true);
            }
        }
        return result;
    }

    private static List<List<Integer>> helper(int index, int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = index; i >= 0; i--) {
            int newTarget = target - candidates[i];
            List<Integer> commonList = new ArrayList<>();
            commonList.add(candidates[i]);
            if (newTarget == 0) {
                result.add(commonList);
            } else if (newTarget >= candidates[0]) {
                List<List<Integer>> lists = helper(i - 1, candidates, newTarget);
                for (List<Integer> pList : lists) {
                    List<Integer> tList = new ArrayList<>(commonList);
                    tList.addAll(pList);
                    result.add(tList);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P40 p40 = new P40();
        int[] array = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = p40.combinationSum2(array, target);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

}
