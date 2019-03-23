package other;

import java.util.Arrays;

public class P132 {

    private void helper(String s, int i, int j, int[] dp) {
        for (; i >= 0 && j <= s.length() - 1 && s.charAt(i) == s.charAt(j); --i, ++j) {
            dp[j] = Math.min(dp[j], (i == 0 ? -1 : dp[i - 1]) + 1);
        }
    }

    public int minCut(String s) {
        if (null == s || 0 == s.length())
            return 0;
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, len - 1);
        for (int i = 0; i < len; i++) {
            helper(s, i, i, dp);
            helper(s, i, i + 1, dp);
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        P132 p132 = new P132();
        System.out.println(p132.minCut("ababababababababababababcbabababababababababababa"));
    }

}
