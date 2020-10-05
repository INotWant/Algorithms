package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_7_4 {

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * DP + 单调栈
     */
//    public int minJump(int[] jump) {
//        int[] dp = new int[jump.length];
//        dp[jump.length - 1] = 1;
//        List<Pair> stack = new ArrayList<>();
//        stack.add(new Pair(jump.length - 1, 1));
//        for (int i = jump.length - 2; i >= 0; i--) {
//            int c = jump[i];
//            int min;
//            if (i + c >= jump.length) {
//                min = 1;
//                dp[i] = min;
//                stack = new ArrayList<>();
//                stack.add(new Pair(jump.length, 0));
//                stack.add(new Pair(i, 1));
//            } else {
//                int value = i + c;
//                int l = 0;
//                int r = stack.size() - 1;
//                while (l < r - 1) {
//                    int mid = (l + r) / 2;
//                    if (stack.get(mid).x > value)
//                        l = mid;
//                    else
//                        r = mid;
//                }
//                min = Math.min(stack.get(r).y + 2, dp[value] + 1);
//                dp[i] = min;
//                while (stack.get(stack.size() - 1).y > min)
//                    stack.remove(stack.size() - 1);
//                stack.add(new Pair(i, min));
//            }
//        }
//        return dp[0];
//    }

    /**
     * 注：这里的 dp[i] 也利用了向前走
     */
    public int minJump(int[] jump) {
        int[] dp = new int[jump.length];
        dp[jump.length - 1] = 1;

        for (int i = jump.length - 1; i >= 0; i--) {
            if (i + jump[i] >= jump.length) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i + jump[i]] + 1;
            }
            // 为什么可以这么剪枝 dp[j] > dp[i]
            // 如果遍历到某dp[j]<dp[i]+1就不需要向右遍历了,因为j到dp.length的值会被当前遍历到的dp[j]更新而不是dp[i]+1
            for (int j = i + 1; j < jump.length && j < i + jump[i] && dp[j] > dp[i]; j++) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[0];
    }

}
