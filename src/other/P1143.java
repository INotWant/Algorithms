package other;

import java.util.HashMap;
import java.util.Map;

public class P1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        // 方法1
//        char[] cs1 = text1.toCharArray();
//        char[] cs2 = text2.toCharArray();
//
//        Map<String, Integer> saveMap = new HashMap<>();
//        return help(cs1, cs2, cs1.length - 1, cs2.length - 1, saveMap);

        // 方法2
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

//    private int help(char[] cs1, char[] cs2, int end1, int end2, Map<String, Integer> saveMap) {
//        if (end1 < 0 || end2 < 0) {
//            return 0;
//        }
//        String key = end1 + "," + end2;
//        Integer result = saveMap.get(key);
//        if (result != null) {
//            return result;
//        }
//
//        int r1 = 0, r2, r3;
//        if (cs1[end1] == cs2[end2]) {
//            r1 = help(cs1, cs2, end1 - 1, end2 - 1, saveMap) + 1;
//        }
//        r2 = help(cs1, cs2, end1 - 1, end2, saveMap);
//        r3 = help(cs1, cs2, end1, end2 - 1, saveMap);
//
//        result = r1 > r2 ? Math.max(r1, r3) : Math.max(r2, r3);
//        saveMap.put(key, result);
//        return result;
//    }

    public static void main(String[] args) {
        P1143 p1143 = new P1143();
        System.out.println(p1143.longestCommonSubsequence("abcde", "ace"));
        System.out.println(p1143.longestCommonSubsequence("abc", "abc"));
        System.out.println(p1143.longestCommonSubsequence("abc", "def"));
    }

}
