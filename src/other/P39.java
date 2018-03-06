package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * url :: https://leetcode.com/problems/combination-sum/description/
 * Dynamic planning
 *
 * @author kissx on 2018/3/6.
 */
public class P39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int index = Arrays.binarySearch(candidates, target);
        index = index < 0 ? -1 * index - 2 : index;
        return helper(index, candidates, target);
    }

    private static List<List<Integer>> helper(int index, int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = index; i >= 0; i--) {
            int count = target / candidates[i];
            for (int j = 1; j <= count; j++) {
                int newTarget = target - j * candidates[i];
                List<Integer> commonList = new ArrayList<>();
                for (int k = 0; k < j; k++)
                    commonList.add(candidates[i]);
                if (newTarget == 0)
                    result.add(commonList);
                else if (newTarget >= candidates[0]) {
                    List<List<Integer>> lists = helper(i - 1, candidates, newTarget);
                    for (List<Integer> pList : lists) {
                        List<Integer> tList = new ArrayList<>(commonList);
                        tList.addAll(pList);
                        result.add(tList);
                    }
                } else if (newTarget < 0)
                    break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        P39 p39 = new P39();

//        int[] array = {2, 3, 6, 7};
//        int target = 7;

        int[] array = {1, 2};
        int target = 3;

        List<List<Integer>> result = p39.combinationSum(array, target);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }


}
