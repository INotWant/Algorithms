package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<Integer> numList = new ArrayList<>();
        for (int num : nums)
            numList.add(num);
        return helper(numList);
    }

    // [NOTE] 假设 numList 已经有序
    private static List<List<Integer>> helper(List<Integer> numList) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();
        posList.add(0);
        for (int i = 1; i < numList.size(); i++) {
            if (!numList.get(i).equals(numList.get(i - 1)))
                posList.add(i);
        }
        if (posList.size() == 1)
            result.add(numList);
        else {
            int count = 0;
            for (int i = posList.get(count++); i < numList.size(); ) {
                Integer temp = numList.get(i);
                if (i != 0)
                    numList.set(i, numList.get(0));
                List<Integer> newNums = new ArrayList<>();
                for (int j = 1; j < numList.size(); j++)
                    newNums.add(numList.get(j));
                newNums.sort(Integer::compareTo);
                List<List<Integer>> resultTemp = helper(newNums);
                for (List<Integer> tempList : resultTemp) {
                    tempList.add(0, temp);
                    result.add(tempList);
                }
                numList.set(0, numList.get(i));
                numList.set(i, temp);
                if (count == posList.size())
                    break;
                i = posList.get(count++);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P47 p47 = new P47();
        int[] nums = {0, 1, 0, 0, 9};
        List<List<Integer>> result = p47.permuteUnique(nums);
        System.out.println(result);
        for (List<Integer> list : result)
            System.out.println(list);
    }
}
