package other;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _leetcode_8_3 {

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Set<String> edgeSet = new HashSet<>();
        int[] sArr = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            sArr[e] = s;
        }
        for (int i = 0; i < hasApple.size(); i++) {
            if (hasApple.get(i)) {
                int t1 = i;
                while (t1 != 0) {
                    int t2 = sArr[t1];
                    edgeSet.add(t2 + "->" + t1);
                    t1 = t2;
                }
            }
        }
        return edgeSet.size() * 2;
    }

}
