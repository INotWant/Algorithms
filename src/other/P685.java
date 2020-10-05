package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P685 {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int max = -1;
        Set<String> set = new HashSet<>();
        String key = "";
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u > max)
                max = u;
            if (v > max)
                max = v;
            String key1 = u + "," + v;
            String key2 = v + "," + u;
            if (set.contains(key2))
                key = key1;
            set.add(key1);
        }
        int[][] arr = new int[max][2];

        int count = max;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            ++arr[u - 1][0];
            if (arr[v - 1][1] == 0)
                --count;
            ++arr[v - 1][1];
        }

        if (!key.equals("")) {
            String[] split = key.split(",");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);

            if (arr[v - 1][1] != 1)
                return new int[]{u, v};
            else
                return new int[]{v, u};
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (count == 1 && arr[v - 1][1] == 1)
                continue;
            if (arr[v - 1][1] == 1 && arr[v - 1][0] == 0)
                continue;
            if (arr[u - 1][0] == 1 && arr[u - 1][1] == 0)
                continue;
            return new int[]{u, v};
        }

        return null;
    }

    public static void main(String[] args) {
        P685 p685 = new P685();
//        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
//        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
//        int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[][] edges = {{4, 2}, {1, 5}, {5, 2}, {5, 3}, {2, 4}};
        System.out.println(Arrays.toString(p685.findRedundantDirectedConnection(edges)));
    }

}
