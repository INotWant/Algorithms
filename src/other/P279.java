package other;

import java.util.LinkedList;
import java.util.Queue;

public class P279 {
    /*
    public int numSquares(int n) {
        Map<Integer, Integer> save = new HashMap<>();
        return help(n, save);
    }

    private int help(int n, Map<Integer, Integer> save) {
        if (save.get(n) != null)
            return save.get(n);

        double sqrt = Math.sqrt(n);
        int s = (int) sqrt;
        if (n - s * s == 0)
            return 1;
        int min = Integer.MAX_VALUE;
        for (int i = (int) sqrt; i > 0; i--) {
            int t = help(n - i * i, save);
            if (min > t)
                min = t;
        }
        save.put(n, min + 1);
        return min + 1;
    }
    */

    // 方法二：为方法一的正序过程！！！
    /*
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
    */

    // 方法三：转换成图论解决 NB
    // 如果两个数 x 到 y 相差一个完全平方数，则连接一条边；我们就得到了一个无权图

    static class Node {
        int val;
        int step;

        public Node(int val, int step) {
            this.val = val;
            this.step = step;
        }
    }

    // BFS --> 用的很巧！
    // 将问题转化成图论
    // 该算法在往队列里面添加节点的时候会 add 很多重复的节点，导致超时，
    // 优化办法是，加入 visited 数组，检查要 add 的数据是否已经出现过了，防止数据重复出现，从而影响图的遍历
    // 同时优化：num - i * i 表达式，只让他计算一次
    // 同时在循环体里面判断退出或返回的条件，而不是在循环体外
    public int numSquares(int n) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));
        // 其实一个真正的图的 BSF 是一定会加上 visited 数组来过滤元素的
        boolean[] visited = new boolean[n + 1];
        while (!queue.isEmpty()) {
            int num = queue.peek().val;
            int step = queue.peek().step;
            queue.remove();

            for (int i = 1; ; i++) {
                int a = num - i * i;
                if (a < 0) {
                    break;
                }
                // 若 a 已经计算到 0 了，就不必再往下执行了
                if (a == 0) {
                    return step + 1;
                }
                if (!visited[a]) {
                    queue.add(new Node(a, step + 1));
                    visited[a] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        P279 obj = new P279();
        System.out.println(obj.numSquares(12));
    }

}
