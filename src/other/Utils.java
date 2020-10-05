package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static int[] dijkstra(int[][] adjMatrix) {
        int nodeNum = adjMatrix.length;

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        int[] result = new int[nodeNum];
        System.arraycopy(adjMatrix[0], 0, result, 0, nodeNum);

        int min;
        int index;

        for (int i = 0; i < nodeNum; i++) {
            min = Integer.MAX_VALUE;
            index = -1;
            for (int j = 0; j < nodeNum; j++) {
                if (!visited.contains(j)) {
                    if (result[j] != -1 && result[j] < min) {
                        min = result[j];
                        index = j;
                    }
                }
            }
            if (index != -1) {
                visited.add(index);
                for (int j = 0; j < adjMatrix[index].length; j++) {
                    if (adjMatrix[index][j] != -1 && !visited.contains(j)) {
                        int distance = adjMatrix[index][j] + result[index];
                        if (result[j] == -1 || distance < result[j]) {
                            result[j] = distance;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] adjMatrix = {
                {-1, 1, 12, -1, -1, -1},
                {-1, -1, 9, 3, -1, -1},
                {-1, -1, -1, -1, 5, -1},
                {-1, -1, 4, -1, 13, 15},
                {-1, -1, -1, -1, -1, 4},
                {-1, -1, -1, -1, -1, -1,}
        };
        int[] result = dijkstra(adjMatrix);
        System.out.println(Arrays.toString(result));
    }

}
