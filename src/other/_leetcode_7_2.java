package other;

import java.util.*;

public class _leetcode_7_2 {

    private static int total = 0;

    public static int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> saveMap = new HashMap<>();
        for (int[] ints : relation) {
            int key = ints[0];
            List<Integer> list = saveMap.get(key);
            if (list == null)
                list = new ArrayList<>();
            list.add(ints[1]);
            saveMap.put(key, list);
        }
        help(0, n, k, saveMap);
        return total;
    }


    private static void help(int curr, int n, int k, Map<Integer, List<Integer>> relation) {
        if (k < 0)
            return;
        if (k == 0 && curr == n - 1) {
            ++total;
        } else {
            List<Integer> list = relation.get(curr);
            if (list != null) {
                for (int nextCurr : list) {
                    help(nextCurr, n, k - 1, relation);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        System.out.println(numWays(5, relation, 3));
    }
}
