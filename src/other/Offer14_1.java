package other;

import java.util.HashMap;
import java.util.Map;

public class Offer14_1 {

//    public int cuttingRope(int n) {
//        if (n == 2)
//            return 1;
//        if (n == 3)
//            return 2;
//        return help(1, n, new HashMap<>());
//    }
//
//    private int help(int start, int end, Map<String, Integer> saveMap) {
//        if (start == end)
//            return 1;
//        String key = start + "," + end;
//        if (saveMap.get(key) != null)
//            return saveMap.get(key);
//        int max = end - start + 1;
//        for (int i = start; i < end; i++) {
//            int r1 = help(start, i, saveMap);
//            int r2 = help(i + 1, end, saveMap);
//            if (r1 * r2 > max)
//                max = r1 * r2;
//        }
//        saveMap.put(key, max);
//        return max;
//    }

    public int cuttingRope(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i < n + 1; i++) {
            long max = Long.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                long t = j * dp[i - j];
                if (t > max)
                    max = t;
            }
            dp[i] = max;
        }
        return (int) (dp[n] % 1000000007);
    }

    public static void main(String[] args) {
        Offer14_1 offer14_1 = new Offer14_1();
        int n = 120;
        System.out.println(offer14_1.cuttingRope(n));
    }

}
