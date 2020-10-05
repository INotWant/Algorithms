package other;

import java.util.HashSet;
import java.util.Set;

public class P403 {

    public boolean canCross(int[] stones) {
        if (stones.length == 2)
            return stones[1] == 1;

        Set<Integer> stonesIndex = new HashSet<>();
        Set<String> saveSet = new HashSet<>();

        for (int stone : stones)
            stonesIndex.add(stone);

        return helper1(0, 1, stones[stones.length - 1], stonesIndex, saveSet);
    }

    private boolean helper1(int curr, int k, int end, Set<Integer> stonesIndex, Set<String> saveSet) {
        String template = curr + ",";
        String key;
        int next = k - 1 + curr;
        if (next != curr) {
            key = template + next;
            if (next == end)
                return true;
            if (stonesIndex.contains(next)) {
                if (!saveSet.contains(key)) {
                    if (helper1(next, k - 1, end, stonesIndex, saveSet))
                        return true;
                    else
                        saveSet.add(key);
                }
            }
        }

        next = k + curr;
        key = template + next;
        if (next == end)
            return true;
        if (stonesIndex.contains(next)) {
            if (!saveSet.contains(key)) {
                if (helper1(next, k, end, stonesIndex, saveSet))
                    return true;
                else
                    saveSet.add(key);
            }
        }

        next = k + curr + 1;
        key = template + next;
        if (next == end)
            return true;
        if (stonesIndex.contains(next)) {
            if (!saveSet.contains(key)) {
                if (helper1(next, k + 1, end, stonesIndex, saveSet))
                    return true;
                else
                    saveSet.add(key);
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int[] stones = {0, 1, 2, 3, 4, 8, 9, 11};
        int[] stones = {0, 2};

        P403 p403 = new P403();
        System.out.println(p403.canCross(stones));
    }

}
