package other;

import java.util.ArrayList;
import java.util.List;

public class P207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            lists.add(new ArrayList<>());

        for (int[] array : prerequisites) {
            int p1 = array[0];
            int p2 = array[1];
            List<Integer> list = lists.get(p2);
            list.add(p1);
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < visited.length; i++) {
            boolean[] booleans = new boolean[numCourses];
            booleans[i] = true;
            if (!visited[i])
                if (!helper(lists, visited, i, booleans))
                    return false;
        }
        return true;
    }

    // DFS
    private boolean helper(List<List<Integer>> lists, boolean[] visited, int pos, boolean[] booleans) {
        List<Integer> list = lists.get(pos);
        for (int newPos : list) {
            visited[newPos] = true;
            if (booleans[newPos])
                return false;
            booleans[newPos] = true;
            boolean result = helper(lists, visited, newPos, booleans);
            if (!result)
                return false;
            booleans[newPos] = false;
        }
        return true;
    }

}
