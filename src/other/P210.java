package other;

import java.util.ArrayList;
import java.util.List;

public class P210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] array = new int[numCourses];
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            lists.add(new ArrayList<>());
        for (int[] tArray : prerequisites) {
            ++array[tArray[0]];
            lists.get(tArray[1]).add(tArray[0]);
        }
        int[] result = new int[numCourses];
        int count = 0;
        while (count < numCourses) {
            int lastCount = count;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0) {
                    result[count++] = i;
                    array[i] = -1;
                    for (int p : lists.get(i))
                        --array[p];
                }
            }
            if (lastCount == count)
                return new int[0];
        }
        return result;
    }

    public static void main(String[] args) {
        P210 p210 = new P210();
        int[][] prerequisites = {{1, 0}};
        p210.findOrder(2, prerequisites);
    }

}
