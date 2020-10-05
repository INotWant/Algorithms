package other;

import java.util.HashMap;
import java.util.Map;

public class P932 {

    private static Map<Integer, int[]> memo = new HashMap<>();

    public int[] beautifulArray(int N) {
        if (memo.get(N) != null) {
            return memo.get(N);
        }

        int[] arr = new int[N];
        int index = 0;

        if (N == 1) {
            arr[0] = 1;
        } else {
            for (int n : beautifulArray((N + 1) / 2)) {
                arr[index++] = n * 2 - 1;
            }
            for (int n : beautifulArray(N / 2)) {
                arr[index++] = n * 2;
            }
        }

        memo.put(N, arr);
        return arr;
    }

}
