package other;

import java.util.ArrayList;
import java.util.List;

public class P46 {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> numList = new ArrayList<>();
        for (int num : nums)
            numList.add(num);
        return helper(numList);
    }

    private static List<List<Integer>> helper(List<Integer> numList) {
        List<List<Integer>> result = new ArrayList<>();
        if (numList.size() == 1) {
            List<Integer> list = new ArrayList<>(numList);
            result.add(list);
        } else if (numList.size() == 2) {
            int a = numList.get(0);
            int b = numList.get(1);
            List<Integer> firstList = new ArrayList<>();
            firstList.add(a);
            firstList.add(b);
            result.add(firstList);
            List<Integer> secondList = new ArrayList<>();
            secondList.add(b);
            secondList.add(a);
            result.add(secondList);
        } else {
            for (int i = 0; i < numList.size(); i++) {
                int temp = numList.get(i);
                if (i != 0)
                    numList.set(i, numList.get(0));
                List<Integer> newNums = new ArrayList<>();
                for (int j = 1; j < numList.size(); j++)
                    newNums.add(numList.get(j));
                List<List<Integer>> resultTemp = helper(newNums);
                for (List<Integer> tempList : resultTemp) {
                    tempList.add(0, temp);
                    result.add(tempList);
                }
                numList.set(0, numList.get(i));
                numList.set(i, temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P46 p46 = new P46();
        int[] nums = {1, 2};
        List<List<Integer>> permute = p46.permute(nums);
        for (List<Integer> list : permute)
            System.out.println(list);
    }

}
