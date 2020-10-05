package other;

import java.util.*;

public class P1557 {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> list : edges) {
            set.add(list.get(1));
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)){
                result.add(i);
            }
        }
        return result;
    }
}
