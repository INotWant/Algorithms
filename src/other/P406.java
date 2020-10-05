package other;

import java.util.*;

public class P406 {

    /**
     * 超时
     */
    /*
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0)
            return people;
        LinkedList<int[]> saveList = new LinkedList<>();
        helper(people, new boolean[people.length], 0, saveList);
        int[][] result = new int[people.length][];
        for (int i = 0; i < people.length; i++)
            result[i] = saveList.get(i);
        return result;
    }

    private boolean helper(int[][] peoples, boolean[] visited, int curr, LinkedList<int[]> result) {
        if (curr == visited.length)
            return true;
        for (int i = 0; i < peoples.length; i++) {
            if (!visited[i] && check(result, peoples[i])) {
                visited[i] = true;
                result.addLast(peoples[i]);
                if (helper(peoples, visited, curr + 1, result))
                    return true;
                result.removeLast();
                visited[i] = false;
            }
        }
        return false;
    }

    private boolean check(List<int[]> result, int[] curr) {
        int h = curr[0];
        int c = curr[1];
        if (result.size() == 0)
            return c == 0;
        int count = 0;
        for (int[] p : result) {
            if (p[0] >= h)
                ++count;
        }
        return count == c;
    }
    // */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0)
            return people;
        Arrays.sort(people, (o1, o2) -> {
            int a1 = o1[0];
            int b1 = o1[1];
            int a2 = o2[0];
            int b2 = o2[1];
            if (b1 > b2)
                return 1;
            else if (b1 < b2)
                return -1;
            return Integer.compare(a1, a2);
        });

        List<int[]> list = new ArrayList<>();
        int i = 0;
        int[] p;
        while (i < people.length) {
            p = people[i];
            if (p[1] == 0) {
                list.add(p);
            } else {
                int num = p[0];
                int c = p[1];
                int count = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j)[0] >= num) {
                        ++count;
                        if (count == c) {
                            ++j;
                            while (j < list.size() && list.get(j)[1] == c)
                                ++j;
                            list.add(j, p);
                            break;
                        }
                    }
                }
            }
            ++i;
        }
        return list.toArray(people);
    }

    public static void main(String[] args) {
        P406 p406 = new P406();

//        int[][] peoples = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
//        int[][] peoples = {{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
        int[][] peoples = {{2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1}, {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}};

        System.out.println(Arrays.deepToString(p406.reconstructQueue(peoples)));
    }

}
